package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import acevedo.EvalFin.org.R;

public class Legales extends AppCompatActivity {

    LinearLayout lyRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legales);

        lyRegresar= findViewById(R.id.lyRegresar);

        lyRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}