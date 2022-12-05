package acevedo.EvalFin.org.MediappRepartidores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
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
import acevedo.EvalFin.org.Adapter.PedidosEntregadosAdapter;
import acevedo.EvalFin.org.Clases.Pedido;
import acevedo.EvalFin.org.Mediapp.MiHistorialPedidos;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;

public class PedidosEntregados extends AppCompatActivity {

    LinearLayout lyRegresar;
    RecyclerView recyclerPedidosEntregados;

    RequestQueue requestQueue;
    List<Pedido> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_entregados);
        lyRegresar=findViewById(R.id.lyRegresar);
        recyclerPedidosEntregados = findViewById(R.id.recyclerPedidosEntregados);
        recyclerPedidosEntregados.setHasFixedSize(true);
        recyclerPedidosEntregados.setLayoutManager(new LinearLayoutManager(this)); //vista lista
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
        SharedPreferences preferences = getSharedPreferences("usuarioLoginRepartidor", Context.MODE_PRIVATE);
        String id_cliente = preferences.getString("id","0");

        String url = Util.RUTA+"list_pedidos_entregados.php?id_cliente="+id_cliente;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pedidos_entregados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id_pedido");
                        String persona_recepcion = jsonObject.getString("persona_recepcion");
                        String celular_persona_recepcion = jsonObject.getString("celular_persona_recepcion");
                        String total = jsonObject.getString("total");
                        String fecha = jsonObject.getString("fecha");
                        String cantidad = jsonObject.getString("cantidad");
                        String nombreProducto = jsonObject.getString("nombreProducto");
                        String img_url = jsonObject.getString("img_url");
                        Pedido pedido = new Pedido(id, 0, 0.0, 0.0, persona_recepcion, celular_persona_recepcion, total, fecha, cantidad, nombreProducto,"0", img_url);
                        mList.add(pedido);
                    }
                    PedidosEntregadosAdapter adapter = new PedidosEntregadosAdapter(PedidosEntregados.this, mList);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //aca poner lo que quieres que se realice al hacer click en el cardView
                            mayorInformacion(view);
                        }
                    });

                    recyclerPedidosEntregados.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PedidosEntregados.this, "No se encontro pedidos entregados", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void mayorInformacion(View view) {
        String producto = mList.get(recyclerPedidosEntregados.getChildAdapterPosition(view)).getNombreProducto();
        String persona = mList.get(recyclerPedidosEntregados.getChildAdapterPosition(view)).getPersona_recepcion();
        String cantidad = mList.get(recyclerPedidosEntregados.getChildAdapterPosition(view)).getCantidad();
        String fecha = mList.get(recyclerPedidosEntregados.getChildAdapterPosition(view)).getFecha();
        String total = mList.get(recyclerPedidosEntregados.getChildAdapterPosition(view)).getTotal();
        String celular = mList.get(recyclerPedidosEntregados.getChildAdapterPosition(view)).getCelular_persona_recepcion();
        String img_url = mList.get(recyclerPedidosEntregados.getChildAdapterPosition(view)).getImg_url();


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogodetpedidoentregado);

        TextView txtProducto = dialog.findViewById(R.id.txtProducto);
        TextView txtNombrePersona = dialog.findViewById(R.id.txtNombrePersona);
        TextView txtFecha = dialog.findViewById(R.id.txtFecha);
        TextView txtCantidad = dialog.findViewById(R.id.txtCantidad);
        TextView txtCelular = dialog.findViewById(R.id.txtCelular);
        TextView txtTotal = dialog.findViewById(R.id.txtTotal);
        ImageView imgPersona = dialog.findViewById(R.id.imgPersona);
        Button btnAceptarRegistroExitoso = dialog.findViewById(R.id.btnAceptarRegistroExitoso);


        txtProducto.setText(producto);
        txtNombrePersona.setText(persona);
        txtCantidad.setText(cantidad);
        txtFecha.setText(fecha);
        txtCelular.setText(celular);
        txtTotal.setText(total);
        Glide.with(this).load(img_url).into(imgPersona);

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