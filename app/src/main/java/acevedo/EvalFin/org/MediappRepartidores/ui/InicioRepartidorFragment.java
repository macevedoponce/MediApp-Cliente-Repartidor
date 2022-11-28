package acevedo.EvalFin.org.MediappRepartidores.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import acevedo.EvalFin.org.MediappRepartidores.verRutaPedido;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;


public class InicioRepartidorFragment extends Fragment {

    TextView txtVerDireccion;
    Double mLatDestino;
    Double mLongDestino;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_inicio_repartidor, container, false);
        txtVerDireccion = vista.findViewById(R.id.txtVerDireccion);
        txtVerDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLatDestino= -12.040881885255898;
                mLongDestino= -75.19286162874772;
//
//                ObtenerRuta(mLatOrigen, mLongOrigen, mLatDestino, mLongDestino);

                Intent i = new Intent(getContext(), verRutaPedido.class);
                i.putExtra("mLatDestino",mLatDestino);
                i.putExtra("mLongDestino",mLongDestino);
                startActivity(i);
            }
        });
        return vista;
    }


}