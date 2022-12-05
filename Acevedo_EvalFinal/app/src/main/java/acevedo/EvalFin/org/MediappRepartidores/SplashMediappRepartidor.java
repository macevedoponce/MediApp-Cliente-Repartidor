package acevedo.EvalFin.org.MediappRepartidores;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import acevedo.EvalFin.org.R;

public class SplashMediappRepartidor extends AppCompatActivity {

    TextView txtDerechos, txtAño, txtMedi, txtApp,txtRepartidores;
    ImageView imgLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_mediapp_repartidor);
        loadLocale();
        //animacion
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        txtDerechos = findViewById(R.id.txtDerechos);
        txtAño = findViewById(R.id.txtAño);
        txtMedi = findViewById(R.id.txtMedi);
        txtApp = findViewById(R.id.txtApp);
        imgLogo = findViewById(R.id.imgLogo);
        txtRepartidores = findViewById(R.id.txtRepartidores);

        txtDerechos.setAnimation(animacion1);
        txtAño.setAnimation(animacion2);
        txtMedi.setAnimation(animacion2);
        txtApp.setAnimation(animacion2);
        imgLogo.setAnimation(animacion2);
        txtRepartidores.setAnimation(animacion1);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashMediappRepartidor.this, LoginMediappRepartidor.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        },3600);
    }

    private void setLocale(String lenguaje) {
        Locale locale = new Locale(lenguaje);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,this.getResources().getDisplayMetrics());
    }

    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("settingsRepartidor", Context.MODE_PRIVATE);
        String lenguaje = preferences.getString("lenguaje","");
        setLocale(lenguaje);
    }
}
