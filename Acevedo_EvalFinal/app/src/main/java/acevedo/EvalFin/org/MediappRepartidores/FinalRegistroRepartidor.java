package acevedo.EvalFin.org.MediappRepartidores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import acevedo.EvalFin.org.R;

public class FinalRegistroRepartidor extends AppCompatActivity {
    Button btnEmpezarARepartir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_registro_repartidor);
        btnEmpezarARepartir = findViewById(R.id.btnEmpezarARepartir);

        btnEmpezarARepartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinalRegistroRepartidor.this, LoginMediappRepartidor.class);
                startActivity(i);
            }
        });
    }
}