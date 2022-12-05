package acevedo.EvalFin.org.Mediapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import acevedo.EvalFin.org.Adapter.CategoriaAdapter;
import acevedo.EvalFin.org.Clases.Categoria;
import acevedo.EvalFin.org.Mediapp.ListProductosActivity;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;


public class CategoriaFragment extends Fragment {
    RecyclerView recyclerCategorias;

    RequestQueue requestQueue;
    List<Categoria> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista =  inflater.inflate(R.layout.fragment_categoria, container, false);
        recyclerCategorias = vista.findViewById(R.id.recyclerCategorias);
        recyclerCategorias.setHasFixedSize(true);
        recyclerCategorias.setLayoutManager(new GridLayoutManager(getContext(),2));
        requestQueue = Volley.newRequestQueue(getContext());
        mList = new ArrayList<>();
        cargarCategoriasAletorias();
        return vista;
    }

    private void cargarCategoriasAletorias() {
        String url = Util.RUTA+"list_categorias.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("categorias");
                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id =jsonObject.getInt("id");
                        String nombre = jsonObject.getString("nombre");
                        int estado =jsonObject.getInt("estado");
                        String ruta_imagen = jsonObject.getString("ruta_imagen");
                        Categoria categoria = new Categoria(id,nombre,estado,ruta_imagen);
                        mList.add(categoria);
                    }
                    CategoriaAdapter adapter = new CategoriaAdapter(getContext(),mList);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mostrarProductos(view);
                        }
                    });

                    recyclerCategorias.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    ///aca nos quedamos
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void mostrarProductos(View view) {
        int id = mList.get(recyclerCategorias.getChildAdapterPosition(view)).getId();
        String nombre = mList.get(recyclerCategorias.getChildAdapterPosition(view)).getNombre();

        Intent i = new Intent(getContext(), ListProductosActivity.class);
        i.putExtra("id_categoria",id);
        i.putExtra("nombre_categoria",nombre);
        startActivity(i);
    }
}