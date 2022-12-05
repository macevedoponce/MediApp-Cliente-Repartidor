package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import acevedo.EvalFin.org.Adapter.PedidoAdapter;
import acevedo.EvalFin.org.Adapter.ProductoAdapter;
import acevedo.EvalFin.org.Clases.Generos;
import acevedo.EvalFin.org.Clases.Pedido;
import acevedo.EvalFin.org.Clases.Producto;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;

public class MisPedidos extends AppCompatActivity {

    LinearLayout lyRegresar;
    RecyclerView recyclerMisPedidos;

    RequestQueue requestQueue;
    List<Pedido> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_pedidos);

        lyRegresar=findViewById(R.id.lyRegresar);
        recyclerMisPedidos = findViewById(R.id.recyclerMisPedidos);
        recyclerMisPedidos.setHasFixedSize(true);
        recyclerMisPedidos.setLayoutManager(new LinearLayoutManager(this)); //vista lista
        requestQueue = Volley.newRequestQueue(this);
        mList = new ArrayList<>();
        cargarProductos();


        lyRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void cargarProductos() {
        SharedPreferences preferences = getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
        String id_cliente = preferences.getString("id","0");

        String url = Util.RUTA+"pedidos_usuario.php?id_cliente="+id_cliente+"&estado=0";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pedidos_cliente");
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
                    PedidoAdapter adapter = new PedidoAdapter(MisPedidos.this, mList);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //aca poner lo que quieres que se realice al hacer click en el cardView
                            mayorInformacion(view);
                        }
                    });

                    recyclerMisPedidos.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MisPedidos.this, "No se encontro pedidos", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void mayorInformacion(View view) {
        String producto = mList.get(recyclerMisPedidos.getChildAdapterPosition(view)).getNombreProducto();
        String cantidad = mList.get(recyclerMisPedidos.getChildAdapterPosition(view)).getCantidad();
        String fecha = mList.get(recyclerMisPedidos.getChildAdapterPosition(view)).getFecha();
        String total = mList.get(recyclerMisPedidos.getChildAdapterPosition(view)).getTotal();
        String pre_unit = mList.get(recyclerMisPedidos.getChildAdapterPosition(view)).getPrecio_unitario();
        String img_url = mList.get(recyclerMisPedidos.getChildAdapterPosition(view)).getImg_url();


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogodetpedido);

        TextView txtProducto = dialog.findViewById(R.id.txtProducto);
        TextView txtFecha = dialog.findViewById(R.id.txtFecha);
        TextView txtCantidad = dialog.findViewById(R.id.txtCantidad);
        TextView txtPrecioUnit = dialog.findViewById(R.id.txtPrecioUnit);
        TextView txtTotal = dialog.findViewById(R.id.txtTotal);
        ImageView imgProducto = dialog.findViewById(R.id.imgProducto);
        Button btnAceptarRegistroExitoso = dialog.findViewById(R.id.btnAceptarRegistroExitoso);


        txtProducto.setText(producto);
        txtCantidad.setText(cantidad);
        txtFecha.setText(fecha);
        txtPrecioUnit.setText(pre_unit);
        txtTotal.setText(total);
        Glide.with(this).load(img_url).into(imgProducto);

        btnAceptarRegistroExitoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);

    }


}