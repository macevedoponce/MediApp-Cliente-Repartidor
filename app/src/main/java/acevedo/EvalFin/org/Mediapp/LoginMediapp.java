package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONObject;

import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;

public class LoginMediapp extends AppCompatActivity {
    EditText edtCorreo, edtPassword;
    Button btnEntrar;
    TextView txtCrearCuenta;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mediapp);
        requestQueue = Volley.newRequestQueue(this);
        edtCorreo =findViewById(R.id.edtCorreo);
        edtPassword =findViewById(R.id.edtPassword);
        btnEntrar =findViewById(R.id.btnEntrar);
        txtCrearCuenta =findViewById(R.id.txtCrearCuenta);

        recuperarPreferencias();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario();
            }
        });

        txtCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginMediapp.this, CrearCuenta.class);
                i.putExtra("tipo_user","1");
                startActivity(i);
            }
        });
    }

    private void validarUsuario() {
        String correo = edtCorreo.getText().toString();
        String password = edtPassword.getText().toString();

        if(!correo.isEmpty() && !password.isEmpty()){
            String url = Util.RUTA + "validar_usuario.php?" +
                    "correo="+correo+
                    "&tipo_user="+1+
                    "&password=" +password;

            url = url.replace(" ","%20");
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    //guardar datos de usuario


                    JSONArray json = response.optJSONArray("usuario");
                    JSONObject jsonObject = null;

                    SharedPreferences preferences = getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    try{
                        jsonObject = json.getJSONObject(0);

                        editor.putString("correo",correo);
                        editor.putString("password",password);
                        editor.putString("id",jsonObject.getString("id"));
                        editor.putString("documento",jsonObject.getString("documento"));
                        editor.putString("nombres",jsonObject.getString("nombres"));
                        editor.putString("apPaterno",jsonObject.getString("apPaterno"));
                        editor.putString("apMaterno",jsonObject.getString("apMaterno"));
                        editor.putString("celular",jsonObject.getString("celular"));
                        editor.putString("f_nacimiento",jsonObject.getString("f_nacimiento"));
                        editor.putInt("genero",jsonObject.getInt("genero"));
                        editor.putInt("tipo_user",jsonObject.getInt("tipo_user"));
                        editor.putInt("tipo_documento",jsonObject.getInt("tipo_documento"));
                        editor.putString("img_url",jsonObject.getString("img_url"));
                        editor.putBoolean("session",true);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    editor.commit();


                    //fin guardar datos de usuario





                    Intent i = new Intent(LoginMediapp.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginMediapp.this, "Datos erroneos, vuelva a ingresar", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }

    private void recuperarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
        edtCorreo.setText(preferences.getString("correo",""));
        edtPassword.setText(preferences.getString("password",""));
        boolean estado = preferences.getBoolean("session",false);
        if( estado == true){
            Intent i = new Intent(LoginMediapp.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}