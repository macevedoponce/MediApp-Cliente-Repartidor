package acevedo.EvalFin.org.MediappRepartidores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import acevedo.EvalFin.org.R;

public class ComenzarEntrega extends AppCompatActivity {

    Button btnRegistrarEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comenzar_entrega);
        btnRegistrarEntrega = findViewById(R.id.btnRegistrarEntrega);

        btnRegistrarEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ComenzarEntrega.this,RegistrarEntrega.class);
                startActivity(i);
            }
        });
    }
}