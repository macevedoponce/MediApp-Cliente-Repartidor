package acevedo.EvalFin.org.Mediapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import acevedo.EvalFin.org.Adapter.CategoriaAdapter;
import acevedo.EvalFin.org.Clases.Categoria;
import acevedo.EvalFin.org.Mediapp.ListProductosActivity;
import acevedo.EvalFin.org.Mediapp.buscarProducto;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;


public class InicioFragment extends Fragment {


    ImageSlider imageSlider;
    RecyclerView recyclerCatAleatorio;
    CardView card_search;

    RequestQueue requestQueue;
    List<Categoria> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista =  inflater.inflate(R.layout.fragment_inicio, container, false);

        imageSlider = vista.findViewById(R.id.imageSlider);
        recyclerCatAleatorio = vista.findViewById(R.id.recyclerCatAleatorio);
        card_search = vista.findViewById(R.id.card_search);
        recyclerCatAleatorio.setHasFixedSize(true);
        recyclerCatAleatorio.setLayoutManager(new GridLayoutManager(getContext(),2));
        requestQueue = Volley.newRequestQueue(getContext());
        mList = new ArrayList<>();
        cargarCategoriasAletorias();

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://images.ctfassets.net/l9x8e72nkkav/4tYaD2Mg8XmY1MY56i2YjU/87d0549d0cc5ca3878db1375715a4768/inka_web_regular_x3_Dermocosmetica_1.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.ctfassets.net/l9x8e72nkkav/7cfpf4d2CChPfVeV9XSg8c/a1cf17c80c85334102bf86ab9768b35c/inka_web_regular_x3_Dermocosmetica_2.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.ctfassets.net/l9x8e72nkkav/4M6jkMUirA1KWL5f7E0ZTQ/028170b57536172e6db3e0e93e592ae6/inka_web_regular_x3_Dermocosmetica_3.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.ctfassets.net/l9x8e72nkkav/5WbgxOYr88mPMUzElA7wZe/7a26f46307fcb457a32175c5c8019c76/inka_web_regular_x3_Cuidado-personal_1.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.ctfassets.net/l9x8e72nkkav/3S2sfCe6lKy6Gy604GBdAb/c7baafd3b12e6c2caf2facd5e231c483/inka_web_regular_x3_Cuidado-personal_2.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://images.ctfassets.net/l9x8e72nkkav/7bI4YsVQAAwsKKf0mBfzkh/3ae03fa673e04db568fdd1ef202783ce/inka_web_regular_x3_Cuidado-personal_3.jpg", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
//                Toast.makeText(getContext(), "id: ", Toast.LENGTH_SHORT).show();
            }
        });

        card_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), buscarProducto.class);
                startActivity(i);
            }
        });

        return vista;
    }

    private void cargarCategoriasAletorias() {
        String url = Util.RUTA+"categorias_aleatorias.php";
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
                    
                    recyclerCatAleatorio.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

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
        int id = mList.get(recyclerCatAleatorio.getChildAdapterPosition(view)).getId();
        String nombre = mList.get(recyclerCatAleatorio.getChildAdapterPosition(view)).getNombre();

        Intent i = new Intent(getContext(), ListProductosActivity.class);
        i.putExtra("id_categoria",id);
        i.putExtra("nombre_categoria",nombre);
        startActivity(i);
    }
}