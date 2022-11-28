package acevedo.EvalFin.org;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                Toast.makeText(FinalRegistroRepartidor.this, "comenzando como repartidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}