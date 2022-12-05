package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import acevedo.EvalFin.org.R;

public class FinalRegistro extends AppCompatActivity {

    Button btnEmpezarAComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_registro);
        btnEmpezarAComprar = findViewById(R.id.btnEmpezarAComprar);

        btnEmpezarAComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinalRegistro.this, LoginMediapp.class);
                startActivity(i);
            }
        });
    }
}