package acevedo.EvalFin.org.Mediapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import acevedo.EvalFin.org.Clases.Lenguajes;
import acevedo.EvalFin.org.Mediapp.LoginMediapp;
import acevedo.EvalFin.org.Mediapp.MiHistorialPedidos;
import acevedo.EvalFin.org.Mediapp.MiPerfil;
import acevedo.EvalFin.org.Mediapp.MisPedidos;
import acevedo.EvalFin.org.R;


public class CuentaFragment extends Fragment {

    TextView txtNombres,txtApellidos;
    ImageView imgCliente;
    LinearLayout lyPerfil, lyPedidos, lyHistorialPedidos;
    CardView cardAtencionCliente, cardCambiarIdioma, cardSizeFuente, cardPoliticas, cardLegales, cardLogOut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_cuenta, container, false);
        txtNombres = vista.findViewById(R.id.txtNombres);
        txtApellidos = vista.findViewById(R.id.txtApellidos);
        imgCliente = vista.findViewById(R.id.imgCliente);
        lyPerfil = vista.findViewById(R.id.lyPerfil);
        lyPedidos = vista.findViewById(R.id.lyPedidos);
        lyHistorialPedidos = vista.findViewById(R.id.lyHistorialPedidos);
        cardAtencionCliente = vista.findViewById(R.id.cardAtencionCliente);
        cardCambiarIdioma = vista.findViewById(R.id.cardCambiarIdioma);
        cardSizeFuente = vista.findViewById(R.id.cardSizeFuente);
        cardPoliticas = vista.findViewById(R.id.cardPoliticas);
        cardLegales = vista.findViewById(R.id.cardLegales);
        cardLogOut = vista.findViewById(R.id.cardLogOut);
        cargarPreferencias();

        cardLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences=getActivity().getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                SharedPreferences pref=getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
                pref.edit().clear().commit();

                Intent i = new Intent(getContext(), LoginMediapp.class);
                startActivity(i);

                getActivity().finish();
            }
        });

        lyPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MiPerfil.class);
                startActivity(i);
            }
        });

        lyPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MisPedidos.class);
                startActivity(i);
            }
        });

        lyHistorialPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MiHistorialPedidos.class);
                startActivity(i);
            }
        });

        cardCambiarIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCambiarLenguaje();
            }
        });

        cardSizeFuente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCambiarSizeFuente();
            }
        });
        return vista;
    }

    private void showCambiarSizeFuente() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fuentesbslayout);

        NumberPicker size = dialog.findViewById(R.id.size);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAceptar = dialog.findViewById(R.id.btnAceptar);



        size.setMinValue(10);
        size.setMaxValue(20);

        size.setValue(13);



        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(lenguaje.getValue() > -1){
//                    btnDocumento.setText(Documentos.getDocumentosArrayList().get(lenguaje.getValue()).getDocumento());
//                    dialog.dismiss();
//                }
                dialog.dismiss();



            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showCambiarLenguaje() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.lenguajesbslayout);

        NumberPicker lenguaje = dialog.findViewById(R.id.lenguaje);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAceptar = dialog.findViewById(R.id.btnAceptar);

        Lenguajes.initLenguajes();


        lenguaje.setMaxValue(Lenguajes.getLenguajesArrayList().size()-1);
        lenguaje.setDisplayedValues(Lenguajes.LenguajesNames());

        lenguaje.setMinValue(0);

        lenguaje.setValue(0);



        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(lenguaje.getValue() > -1){
//                    btnDocumento.setText(Documentos.getDocumentosArrayList().get(lenguaje.getValue()).getDocumento());
//                    dialog.dismiss();
//                }
                dialog.dismiss();



            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void cargarPreferencias() {
        SharedPreferences preferences = getActivity().getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombres","");
        String apPat = preferences.getString("apPaterno","");
        String apMat = preferences.getString("apMaterno","");
        String url_img = preferences.getString("img_url","https://d1bvpoagx8hqbg.cloudfront.net/259/0f326ce8a41915e8b1d21ffaee087fae.jpg");

        txtNombres.setText(nombre);
        txtApellidos.setText(apPat +" "+apMat);
        Glide.with(imgCliente).load(url_img).centerCrop().into(imgCliente);
    }
}