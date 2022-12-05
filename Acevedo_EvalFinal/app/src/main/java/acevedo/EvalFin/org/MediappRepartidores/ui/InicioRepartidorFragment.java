package acevedo.EvalFin.org.MediappRepartidores.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import acevedo.EvalFin.org.Adapter.PedidoAdapter;
import acevedo.EvalFin.org.Adapter.PedidoAdapterRepartidor;
import acevedo.EvalFin.org.Clases.EnableServices;
import acevedo.EvalFin.org.Clases.GPSTracker;
import acevedo.EvalFin.org.Clases.Pedido;
import acevedo.EvalFin.org.Clases.PermissionGranted;
import acevedo.EvalFin.org.Mediapp.MisPedidos;
import acevedo.EvalFin.org.MediappRepartidores.verRutaPedido;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Context.LOCATION_SERVICE;


public class InicioRepartidorFragment extends Fragment {

    RecyclerView recyclerPedidos;
    TextView txtNombreRepartidor;

    RequestQueue requestQueue;
    List<Pedido> mList;

    //nuevo
    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;
    Location location;
    GPSTracker gpsTracker;
    private View pop = null;
    LocationManager locationManager;
    //fin nuevo



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.fragment_inicio_repartidor, container, false);


        //nuevo

        gpsTracker = new GPSTracker(getContext());
        location = gpsTracker.getLocation();
        request = Volley.newRequestQueue(getContext());


        if (PermissionGranted.checkPermiso(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {   //Si tiene el permiso de gps

            locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {                             //Si el gps esta activo
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                EnableServices enableServices = new EnableServices();
                enableServices.show(fragmentManager, null);


            } else {
                //Toast.makeText(this,"tienes activar gps",Toast.LENGTH_SHORT).show();

            }

        } else {

            PermissionGranted.pedirPermiso(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        }

        //fin nuevo
        txtNombreRepartidor = vista.findViewById(R.id.txtNombreRepartidor);
        recyclerPedidos = vista.findViewById(R.id.recyclerPedidos);
        recyclerPedidos.setHasFixedSize(true);
        recyclerPedidos.setLayoutManager(new LinearLayoutManager(getContext())); //vista lista
        requestQueue = Volley.newRequestQueue(getContext());
        mList = new ArrayList<>();
        cargarPedidos();
        cargarPreferenciasRepartidor();
        return vista;
    }

    private void cargarPedidos() {
        String url = Util.RUTA+"list_pedidos.php?"+"&estado=0";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pedidos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        Double latitud = jsonObject.getDouble("latitud");
                        Double longitud = jsonObject.getDouble("longitud");
                        String persona_recepcion = jsonObject.getString("persona_recepcion");
                        String celular_persona_recepcion = jsonObject.getString("celular_persona_recepcion");
                        String total = jsonObject.getString("total");
                        String fecha = jsonObject.getString("fecha");
                        String cantidad = jsonObject.getString("cantidad");
                        String precio_unitario = jsonObject.getString("pre_unit");
                        String nombreProducto = jsonObject.getString("nombreProducto");
                        String img_url = jsonObject.getString("img_url");
                        int estado = jsonObject.getInt("estado");
                        Pedido pedido = new Pedido(id, estado, longitud, latitud, persona_recepcion, celular_persona_recepcion, total, fecha, cantidad, nombreProducto,precio_unitario, img_url);
                        mList.add(pedido);
                    }
                    PedidoAdapterRepartidor adapter = new PedidoAdapterRepartidor(getContext(), mList);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //aca poner lo que quieres que se realice al hacer click en el cardView
                            verDireccion(view);
                        }
                    });

                    recyclerPedidos.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se encontro pedidos", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void verDireccion(View view) {

        double mLatDestino = mList.get(recyclerPedidos.getChildAdapterPosition(view)).getLatitud();
        double mLongDestino = mList.get(recyclerPedidos.getChildAdapterPosition(view)).getLongitud();
        int id_pedido = mList.get(recyclerPedidos.getChildAdapterPosition(view)).getId();

        //nuevo



        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);
        }
        LocationManager ubicacionActual = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacionActual.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Util.coordenadas.setOrigenLat(loc.getLatitude());
        Util.coordenadas.setOriggenLng(loc.getLongitude());
        Util.coordenadas.setDestinoLat(mLatDestino);
        Util.coordenadas.setDestinoLng(mLongDestino);

        //fin nuevo

        //validar si tenemos permisos de ubicación

        ObtenerRuta(id_pedido,String.valueOf(Util.coordenadas.getOrigenLat()), String.valueOf(Util.coordenadas.getOriggenLng()),
                String.valueOf(Util.coordenadas.getDestinoLat()), String.valueOf(Util.coordenadas.getDestinoLng()));

//        Intent i = new Intent(getContext(), verRutaPedido.class);
//        i.putExtra("mLatDestino",mLatDestino);
//        i.putExtra("mLongDestino",mLongDestino);
//        i.putExtra("id_pedido",id_pedido+"");
//        startActivity(i);
    }

    private void ObtenerRuta(int id_pedido,String latInicial, String lngInicial, String latFinal, String lngFinal) {
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + latInicial + "," + lngInicial + "&destination=" + latFinal + "," + lngFinal + "&key=AIzaSyAWWM30rvrAx-80fqgVqrwIW2Seg-cgkX0&mode=drive";

        //Toast.makeText(this, latInicial, Toast.LENGTH_SHORT).show();

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jRoutes = null;
                JSONArray jLegs = null;
                JSONArray jSteps = null;
                //Toast.makeText(MainActivityInicioMapas.this, "hola", Toast.LENGTH_SHORT).show();

                try {

                    jRoutes = response.getJSONArray("routes");

                    //Toast.makeText(MainActivityInicioMapas.this, String.valueOf(response), Toast.LENGTH_SHORT).show();
                    /** Traversing all routes */
                    for(int i=0;i<jRoutes.length();i++){
                        //Log.d("Routesssssssssss: ", String.valueOf(i));
                        jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                        List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();
                        //Log.d("Routesssssssssss: ", String.valueOf(jLegs));
                        /** Traversing all legs */
                        for(int j=0;j<jLegs.length();j++){
                            jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");

                            /** Traversing all steps */
                            for(int k=0;k<jSteps.length();k++){
                                //Log.d("steepssssssssssss", "aaaaaaaaaaaaaa");
                                //Toast.makeText(MainActivityInicioMapas.this, String.valueOf(jSteps.length()), Toast.LENGTH_SHORT).show();
                                String polyline = "";
                                polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                                List<LatLng> list = decodePoly(polyline);
                                //Toast.makeText(MainActivityInicioMapas.this, String.valueOf(list.get(0)), Toast.LENGTH_LONG).show();

                                /** Traversing all points */
                                for(int l=0;l<list.size();l++){
                                    //Toast.makeText(MainActivityInicioMapas.this, String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
                                    HashMap<String, String> hm = new HashMap<String, String>();
                                    hm.put("lat", Double.toString(((LatLng)list.get(l)).latitude) );
                                    hm.put("lng", Double.toString(((LatLng)list.get(l)).longitude) );
                                    path.add(hm);
                                    //Toast.makeText(MainActivityInicioMapas.this, String.valueOf(path.size()), Toast.LENGTH_SHORT).show();
                                }
                            }
                            //Log.d("AAAAAAAAAAAAAAAAA", String.valueOf(Utilidades.routes.size()));
                            Util.routes.add(path);
                            //Log.d("AAAAAAAAAAAAAAAAA", String.valueOf(Utilidades.routes.size()));
                            Intent intent = new Intent(getContext(), verRutaPedido.class);
                            intent.putExtra("id_pedido",id_pedido+"");
                            startActivity(intent);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                Log.d("ERROR: ", error.toString());
            }
        }
        );

        request.add(jsonObjectRequest);
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    private void cargarPreferenciasRepartidor() {
        SharedPreferences preferences = getContext().getSharedPreferences("usuarioLoginRepartidor", Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombres","");
        txtNombreRepartidor.setText(nombre);

    }
}