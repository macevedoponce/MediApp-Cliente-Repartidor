package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import acevedo.EvalFin.org.Adapter.ProductoAdapter;
import acevedo.EvalFin.org.Clases.Producto;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;

public class ListProductosActivity extends AppCompatActivity {

    LinearLayout lyRegresar;
    TextView txtNombreCategoria;

    RecyclerView recyclerProductos;

    RequestQueue requestQueue;
    List<Producto> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_productos);
        lyRegresar = findViewById(R.id.lyRegresar);
        txtNombreCategoria = findViewById(R.id.txtNombreCategoria);
        recyclerProductos = findViewById(R.id.recyclerProductos);
        recyclerProductos.setHasFixedSize(true);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this)); //vista lista
        //recyclerProductos.setLayoutManager(new GridLayoutManager(this,3));//vista grid
        requestQueue = Volley.newRequestQueue(this);
        mList = new ArrayList<>();
        cargarProductos();

        lyRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String nombre_categoria = getIntent().getStringExtra("nombre_categoria");
        txtNombreCategoria.setText(nombre_categoria);

    }

    private void cargarProductos() {
        int id_categoria = getIntent().getIntExtra("id_categoria",0);
        String url = Util.RUTA+"list_productos.php?id_categoria="+id_categoria;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("productos");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                int id_categoria = jsonObject.getInt("id_categoria");
                                String codigo = jsonObject.getString("codigo");
                                String nombre = jsonObject.getString("nombre");
                                Double precio_venta = jsonObject.getDouble("precio_venta");
                                int stock = jsonObject.getInt("stock");
                                String presentacion = jsonObject.getString("presentacion");
                                String ruta_imagen = jsonObject.getString("ruta_imagen");
                                String descripcion = jsonObject.getString("descripcion");
                                int estado = jsonObject.getInt("estado");
                                Producto producto = new Producto(id, id_categoria, codigo, nombre, precio_venta, stock, presentacion, ruta_imagen, descripcion, estado);
                                mList.add(producto);
                            }
                            ProductoAdapter adapter = new ProductoAdapter(ListProductosActivity.this, mList);
                            adapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mayorInformacion(view);
                                }
                            });

                            recyclerProductos.setAdapter(adapter);

                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListProductosActivity.this, "No se encontro productos", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void mayorInformacion(View view) {
        int id = mList.get(recyclerProductos.getChildAdapterPosition(view)).getId();
        int id_categoria = mList.get(recyclerProductos.getChildAdapterPosition(view)).getId_categoria();
        int stock = mList.get(recyclerProductos.getChildAdapterPosition(view)).getStock();
        int estado = mList.get(recyclerProductos.getChildAdapterPosition(view)).getEstado();
        String codigo = mList.get(recyclerProductos.getChildAdapterPosition(view)).getCodigo();
        String nombre = mList.get(recyclerProductos.getChildAdapterPosition(view)).getNombre();
        String presentacion = mList.get(recyclerProductos.getChildAdapterPosition(view)).getPresentacion();
        String ruta_imagen = mList.get(recyclerProductos.getChildAdapterPosition(view)).getRuta_imagen();
        Double precio_venta =mList.get(recyclerProductos.getChildAdapterPosition(view)).getPrecio_venta();
        String descripcion =mList.get(recyclerProductos.getChildAdapterPosition(view)).getDescripcion();
        Intent i = new Intent(ListProductosActivity.this,DetalleProductoActivity.class);
        i.putExtra("id",id);
        i.putExtra("id_categoria",id_categoria);
        i.putExtra("stock",stock);
        i.putExtra("estado",estado);
        i.putExtra("codigo",codigo);
        i.putExtra("nombre",nombre);
        i.putExtra("presentacion",presentacion);
        i.putExtra("ruta_imagen",ruta_imagen);
        i.putExtra("precio_venta",precio_venta);
        i.putExtra("descripcion",descripcion);
        startActivity(i);
    }
}