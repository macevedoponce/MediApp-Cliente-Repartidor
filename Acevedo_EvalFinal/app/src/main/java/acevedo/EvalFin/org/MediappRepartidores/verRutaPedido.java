package acevedo.EvalFin.org.MediappRepartidores;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;
import acevedo.EvalFin.org.databinding.ActivityVerRutaPedidoBinding;

public class verRutaPedido extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityVerRutaPedidoBinding binding;

    Button btnComenzar, btnCancelar,btnRegistrarEntrega;

    RequestQueue requestQueue;
    StringRequest stringRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);
        binding = ActivityVerRutaPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_ver_ruta);
        mapFragment.getMapAsync(this);
        btnComenzar = findViewById(R.id.btnComenzar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnRegistrarEntrega = findViewById(R.id.btnRegistrarEntrega);
        btnRegistrarEntrega.setVisibility(View.GONE);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
            }
        });

        btnRegistrarEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_pedido = getIntent().getStringExtra("id_pedido");
                Intent i = new Intent(verRutaPedido.this,RegistrarEntrega.class);
                i.putExtra("id_pedido",id_pedido);
                startActivity(i);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });


    }

    private void actualizarDatos(){

        String id_pedido = getIntent().getStringExtra("id_pedido");
        String estado = "1";

        SharedPreferences preferences = getSharedPreferences("usuarioLoginRepartidor", Context.MODE_PRIVATE);
        String id_repartidor = preferences.getString("id","");

        String url = Util.RUTA+"actualizar_entrega.php";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(verRutaPedido.this, "Iniciando ...", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q="+Util.coordenadas.getDestinoLat()+","+Util.coordenadas.getDestinoLng()+"&mode=1"));
                i.setPackage("com.google.android.apps.maps");

                if(i.resolveActivity(getPackageManager()) != null){
                    btnRegistrarEntrega.setVisibility(View.VISIBLE);
                    btnComenzar.setVisibility(View.GONE);
                    btnCancelar.setVisibility(View.GONE);
                    startActivity(i);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(verRutaPedido.this, "error BD", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("id_pedido",id_pedido);
                parametros.put("id_repartidor",id_repartidor);
                parametros.put("estado",estado);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //nuevo

        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;

        for(int i = 0; i< Util.routes.size(); i++){
            Log.d("CCCCCCCCCCCCCCC", String.valueOf(Util.routes.size()));
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();

            // Obteniendo el detalle de la ruta
            List<HashMap<String, String>> path = Util.routes.get(i);

            // Obteniendo todos los puntos y/o coordenadas de la ruta
            for(int j=0;j<path.size();j++){
                HashMap<String,String> point = path.get(j);

                double lat = Double.parseDouble(Objects.requireNonNull(point.get("lat")));
                double lng = Double.parseDouble(Objects.requireNonNull(point.get("lng")));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            // Agregamos todos los puntos en la ruta al objeto LineOptions
            lineOptions.addAll(points);
            //Definimos el grosor de las Polilíneas
            lineOptions.width(9);
            //Definimos el color de la Polilíneas
            lineOptions.color(ContextCompat.getColor(verRutaPedido.this, R.color.primary));
        }

        assert lineOptions != null;
        mMap.addPolyline(lineOptions);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.point)).position(new LatLng(Util.coordenadas.getOrigenLat(), Util.coordenadas.getOriggenLng())).title("Tú posición"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(Util.coordenadas.getDestinoLat(), Util.coordenadas.getDestinoLng())).title("Lugar de entrega"));
        LatLng destino = new LatLng(Util.coordenadas.getDestinoLat(), Util.coordenadas.getDestinoLng());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destino, 15));

        //fin nuevo
    }

}