package acevedo.EvalFin.org.Mediapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import acevedo.EvalFin.org.Clases.Documentos;
import acevedo.EvalFin.org.MediappRepartidores.FinalRegistroRepartidor;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;

public class DatosFinales extends AppCompatActivity {
    Button btnDocumento, btnCrearCuenta;
    EditText edtNumDocumento;
    CheckBox checkboxTerminos, checkboxUsoDatos;
    TextView txtTerminoYCondiciones, txtPoliticasDePrivacidad,txtUsoDeDatos, txtVolver;
    String tipo_documento="nada";
    TextInputLayout tilNumDocumento;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_finales);
        btnDocumento = findViewById(R.id.btnDocumento);
        requestQueue = Volley.newRequestQueue(this);
        btnCrearCuenta = findViewById(R.id.btnCrearCuentaFinal);
        checkboxTerminos = findViewById(R.id.checkboxTerminos);
        checkboxUsoDatos = findViewById(R.id.checkboxUsoDatos);
        txtTerminoYCondiciones = findViewById(R.id.txtTerminosYCondiciones);
        txtPoliticasDePrivacidad = findViewById(R.id.txtPoliticasDePrivacidad);
        txtUsoDeDatos = findViewById(R.id.txtUsoDeDatos);
        txtVolver = findViewById(R.id.txtVolverFinal);
        edtNumDocumento = findViewById(R.id.edtNumDocumento);
        tilNumDocumento = findViewById(R.id.tilNumDocumento);
        btnDocumento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDocumento();
            }
        });
        txtTerminoYCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DatosFinales.this, "Terminos y condiciones", Toast.LENGTH_SHORT).show();
            }
        });

        txtPoliticasDePrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DatosFinales.this, "Politicas de privacidad", Toast.LENGTH_SHORT).show();
            }
        });

        txtUsoDeDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DatosFinales.this, "Uso de mis datos registrados", Toast.LENGTH_SHORT).show();
            }
        });

        txtVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {

                String correo = getIntent().getStringExtra("correo");
                String password = getIntent().getStringExtra( "password" );
                String tipo_user = getIntent().getStringExtra("tipo_user");
                String nombres = getIntent().getStringExtra("nombres");
                String apPat = getIntent().getStringExtra("apPat");
                String apMat = getIntent().getStringExtra("apMat");
                String celular = getIntent().getStringExtra("celular");
                String f_nacimiento = getIntent().getStringExtra("f_nacimiento");
                String imagen = "imagenes/user_default.jpg";
                int genero = getIntent().getIntExtra("genero",4);

                String numDocumento = edtNumDocumento.getText().toString();
                boolean c1 = checkboxTerminos.isChecked();
                boolean c2 = checkboxUsoDatos.isChecked();

                if(!tipo_documento.equals("nada")){
                    if(!numDocumento.isEmpty()){
                        if(c1 == true && c2 == true){

                            String url = Util.RUTA + "registrar_usuario.php?" +
                                    "correo="+correo+
                                    "&password=" +password+
                                    "&tipo_user=" +tipo_user+
                                    "&nombres=" +nombres+
                                    "&apPat=" +apPat+
                                    "&apMat=" +apMat+
                                    "&celular=" +celular+
                                    "&f_nacimiento=" +f_nacimiento+
                                    "&genero=" +genero+
                                    "&tipo_documento=" +tipo_documento+
                                    "&imagen=" + imagen +
                                    "&numDocumento=" +numDocumento;

                            url = url.replace(" ","%20");
                            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    if(tipo_user.equals("1")){

                                        SharedPreferences preferences = getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("correo",correo);
                                        editor.putString("password",password);
                                        editor.commit();



                                        Intent i = new Intent(DatosFinales.this,FinalRegistro.class);
                                        startActivity(i);
                                    }
                                    if(tipo_user.equals("2")){

                                        SharedPreferences preferences = getSharedPreferences("usuarioLoginRepartidor", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("correo",correo);
                                        editor.putString("password",password);
                                        editor.commit();


                                        Intent i = new Intent(DatosFinales.this, FinalRegistroRepartidor.class);
                                        startActivity(i);
                                    }else{
                                        Toast.makeText(DatosFinales.this, "creación exitosa", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(DatosFinales.this, "lo sentimos, no pudimos crear su cuenta", Toast.LENGTH_SHORT).show();
                                }
                            });
                            requestQueue.add(jsonObjectRequest);

                        }else{
                            checkboxTerminos.setOutlineSpotShadowColor(Color.RED);
                            checkboxUsoDatos.setOutlineAmbientShadowColor(Color.RED);
                        }
                    }else{
                        tilNumDocumento.setError("Ingrese número de documento");
                    }

                }else{
                    btnDocumento.setText("Seleccione Tipo");
                    btnDocumento.setTextColor(Color.RED);
                }


            }
        });



    }


    private void showDialogDocumento() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.documentosbslayout);

        NumberPicker documento = dialog.findViewById(R.id.genero);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAceptar = dialog.findViewById(R.id.btnAceptar);

        Documentos.initDocumentos();


        documento.setMaxValue(Documentos.getDocumentosArrayList().size()-1);
        documento.setDisplayedValues(Documentos.documentosNames());


        documento.setMinValue(0);


        documento.setValue(1);



        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tipo_documento = documento.getValue()+"";

                if(documento.getValue() > -1){
                    btnDocumento.setText(Documentos.getDocumentosArrayList().get(documento.getValue()).getDocumento());
                    dialog.dismiss();
                }

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

}