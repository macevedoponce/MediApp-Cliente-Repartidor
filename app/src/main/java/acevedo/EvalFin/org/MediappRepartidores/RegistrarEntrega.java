package acevedo.EvalFin.org.MediappRepartidores;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import acevedo.EvalFin.org.Clases.Generos;
import acevedo.EvalFin.org.Mediapp.MainActivity;
import acevedo.EvalFin.org.Mediapp.RegistrarCompraActivity;
import acevedo.EvalFin.org.MediappRepartidores.ui.InicioRepartidorFragment;
import acevedo.EvalFin.org.R;

public class RegistrarEntrega extends AppCompatActivity {

    Button btnGuardarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_entrega);
        btnGuardarPedido = findViewById(R.id.btnGuardarPedido);

        btnGuardarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogBox();
            }
        });
    }

    private void ShowDialogBox() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogoregexitrepartidor);

        Button btnAceptarRegistroExitoso = dialog.findViewById(R.id.btnAceptarRegistroExitosoRepartidor);

        btnAceptarRegistroExitoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrarEntrega.this, MainActivityRepartidor.class);
                startActivity(i);
                finish();
            }
        });

        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.CENTER);
    }
}