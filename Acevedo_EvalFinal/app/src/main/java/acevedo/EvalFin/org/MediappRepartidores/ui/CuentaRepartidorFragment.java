package acevedo.EvalFin.org.MediappRepartidores.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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

import java.util.Locale;

import acevedo.EvalFin.org.Clases.Lenguajes;
import acevedo.EvalFin.org.Mediapp.LoginMediapp;
import acevedo.EvalFin.org.Mediapp.MiHistorialPedidos;
import acevedo.EvalFin.org.Mediapp.MiPerfil;
import acevedo.EvalFin.org.MediappRepartidores.LoginMediappRepartidor;
import acevedo.EvalFin.org.MediappRepartidores.MainActivityRepartidor;
import acevedo.EvalFin.org.MediappRepartidores.PedidosEntregados;
import acevedo.EvalFin.org.R;


public class CuentaRepartidorFragment extends Fragment {

    LinearLayout lyPerfilRepartidor, lyPedidosEntregados;
    CardView cardCambiarIdioma,cardSizeFuente, cardLogOut;
    ImageView imgRepartidor;
    TextView txtNombres, txtApellidos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_cuenta_repartidor, container, false);
        lyPerfilRepartidor = vista.findViewById(R.id.lyPerfilRepartidor);
        lyPedidosEntregados = vista.findViewById(R.id.lyPedidosEntregados);
        cardCambiarIdioma = vista.findViewById(R.id.cardCambiarIdioma);
        cardSizeFuente = vista.findViewById(R.id.cardSizeFuente);
        cardLogOut = vista.findViewById(R.id.cardLogOut);
        txtNombres = vista.findViewById(R.id.txtNombresRepartidor);
        txtApellidos = vista.findViewById(R.id.txtApellidosRepartidor);
        imgRepartidor = vista.findViewById(R.id.imgRepartidor);
        cargarPreferencias();

        cardLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences=getActivity().getSharedPreferences("usuarioLoginRepartidor", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                SharedPreferences pref=getActivity().getSharedPreferences("usuarioRepartidor", Context.MODE_PRIVATE);
                pref.edit().clear().commit();

                Intent i = new Intent(getContext(), LoginMediappRepartidor.class);
                startActivity(i);

                getActivity().finish();
            }
        });

        lyPerfilRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MiPerfil.class);
                i.putExtra("idUser",2);
                startActivity(i);
            }
        });

        lyPedidosEntregados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PedidosEntregados.class);
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
                String idioma="Español";
                if(lenguaje.getValue() > -1){
                    idioma = Lenguajes.getLenguajesArrayList().get(lenguaje.getValue()).getLenguaje();
                }
                if(idioma.equals("Ingles")){
                    setLocale("en");
                }else{
                    setLocale("es");
                } dialog.dismiss();
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

    private void setLocale(String lenguaje) {
        Locale locale = new Locale(lenguaje);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getContext().getResources().updateConfiguration(configuration,this.getResources().getDisplayMetrics());

        SharedPreferences preferences = getActivity().getSharedPreferences("settingsRepartidor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("lenguaje",lenguaje);
        editor.commit();
    }

    public void loadLocale(){
        SharedPreferences preferences = getActivity().getSharedPreferences("settingsRepartidor", Context.MODE_PRIVATE);
        String lenguaje = preferences.getString("lenguaje","");
        setLocale(lenguaje);
    }

    private void cargarPreferencias() {
        SharedPreferences preferences = getActivity().getSharedPreferences("usuarioLoginRepartidor", Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombres","");
        String apPat = preferences.getString("apPaterno","");
        String apMat = preferences.getString("apMaterno","");
        String url_img = preferences.getString("img_url","https://d1bvpoagx8hqbg.cloudfront.net/259/0f326ce8a41915e8b1d21ffaee087fae.jpg");

        txtNombres.setText(nombre);
        txtApellidos.setText(apPat +" "+apMat);
        Glide.with(imgRepartidor).load(url_img).centerCrop().into(imgRepartidor);
    }
}