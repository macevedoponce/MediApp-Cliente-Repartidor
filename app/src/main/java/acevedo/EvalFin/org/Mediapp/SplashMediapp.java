package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import acevedo.EvalFin.org.R;

public class SplashMediapp extends AppCompatActivity {

    TextView txtDerechos, txtAño, txtMedi, txtApp;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_mediapp);

        //animacion
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        txtDerechos = findViewById(R.id.txtDerechos);
        txtAño = findViewById(R.id.txtAño);
        txtMedi = findViewById(R.id.txtMedi);
        txtApp = findViewById(R.id.txtApp);
        imgLogo = findViewById(R.id.imgLogo);

        txtDerechos.setAnimation(animacion1);
        txtAño.setAnimation(animacion2);
        txtMedi.setAnimation(animacion2);
        txtApp.setAnimation(animacion2);
        imgLogo.setAnimation(animacion2);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashMediapp.this,LoginMediapp.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        },3600);
    }
}