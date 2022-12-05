package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import acevedo.EvalFin.org.R;

public class CrearCuenta extends AppCompatActivity {
    TextInputLayout tilCorreoCreate, tilPasswordCreate,tilRepetirPasswordCreate;

    EditText edtCorreo, edtPassword, edtPasswordRep;
    Button btnSiguiente;
    TextView txtCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        tilCorreoCreate = findViewById(R.id.tilCorreoCreate);
        tilPasswordCreate = findViewById(R.id.tilPasswordCreate);
        tilRepetirPasswordCreate = findViewById(R.id.tilRepetirPasswordCreate);

        edtCorreo = findViewById(R.id.edtCorreoCreate);
        edtPassword = findViewById(R.id.edtPasswordCreate);
        edtPasswordRep = findViewById(R.id.edtRepetirPasswordCreate);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtCancelar = findViewById(R.id.txtCancelar);



        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correo = edtCorreo.getText().toString();
                String pass = edtPassword.getText().toString();
                String passRep = edtPasswordRep.getText().toString();
                String tipo_user = getIntent().getStringExtra("tipo_user");


                if(!correo.isEmpty() && !pass.isEmpty() && !passRep.isEmpty()){
                    if(pass.equals(passRep)){
                        Intent i = new Intent(CrearCuenta.this,MoreInfo.class);
                        i.putExtra("correo", correo);
                        i.putExtra("password", passRep );
                        i.putExtra("tipo_user", tipo_user);
                        startActivity(i);
                    }else{
                        tilRepetirPasswordCreate.setError("Contraseñas diferentes");
                    }
                }else{
                    tilCorreoCreate.setError("Ingrese un correo valido");
                }
            }
        });

        txtCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}