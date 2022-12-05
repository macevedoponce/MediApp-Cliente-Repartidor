package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import acevedo.EvalFin.org.Clases.Generos;
import acevedo.EvalFin.org.MediappRepartidores.FinalRegistroRepartidor;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;

public class RegistrarCompraActivity extends AppCompatActivity {

    RadioButton radBtnYo, radBtnOtraPersona;
    TextView latitud,longitud,txtNombreProducto,txtPrecioUnitario,txtCantidad,txtTotal,txtCambiarDirec;
    EditText edtNombres, edtCelular;
    Button btnConfirmarPedido;
    //int id_cliente;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_compra);
        latitud = findViewById(R.id.txtLatitud);
        longitud = findViewById(R.id.txtLongitud);
        radBtnOtraPersona = findViewById(R.id.radBtnOtraPersona);
        txtNombreProducto = findViewById(R.id.txtNombreProducto);
        txtPrecioUnitario = findViewById(R.id.txtPrecioUnitario);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtTotal = findViewById(R.id.txtTotal);
        radBtnYo = findViewById(R.id.radBtnYo);
        edtCelular = findViewById(R.id.edtCelular);
        edtNombres = findViewById(R.id.edtNombres);
        txtCambiarDirec = findViewById(R.id.txtCambiarDirec);
        btnConfirmarPedido = findViewById(R.id.btnConfirmarPedido);
        requestQueue = Volley.newRequestQueue(this);
        radBtnOtraPersona.setChecked(true); // por defecto viene marcado que otra persona recibirá el pedido
        cargarDatos();
    }

    private void cargarDatos() {

        double mLat = getIntent().getDoubleExtra("latitud",0);
        double mLong = getIntent().getDoubleExtra("longitud",0);
        int id = getIntent().getIntExtra("id_producto",0);
        int stock = getIntent().getIntExtra("stock",0);
        String nombre = getIntent().getStringExtra("nombre");
        Double precio_venta = getIntent().getDoubleExtra("precio_venta",0.0);
        int cantidad = getIntent().getIntExtra("cantidad",0);
        String precio_total = getIntent().getStringExtra("precio_total");

        latitud.setText(mLat+"");
        longitud.setText(mLong+"");
        txtNombreProducto.setText(nombre);
        txtPrecioUnitario.setText(precio_venta+"");
        txtCantidad.setText(cantidad+"");
        txtTotal.setText(precio_total);

        radBtnYo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarPreferencias();
                edtCelular.setEnabled(false);
                edtNombres.setEnabled(false);
            }
        });
        radBtnOtraPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCelular.setText("");
                edtNombres.setText("");
                edtCelular.setEnabled(true);
                edtNombres.setEnabled(true);
            }
        });

        txtCambiarDirec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrarCompraActivity.this,ubiClienteActivity.class);
                i.putExtra("id_producto",id);
                i.putExtra("stock",stock);
                i.putExtra("nombre",nombre);
                i.putExtra("precio_venta",precio_venta);
                i.putExtra("cantidad",cantidad);
                i.putExtra("precio_total",precio_total);
                startActivity(i);
                finish();
            }
        });


        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
                String id_cliente = preferences.getString("id","");

                String persona_recepcion = edtNombres.getText().toString();
                String celular_recepcion =edtCelular.getText().toString();

                if(persona_recepcion.isEmpty() && celular_recepcion.isEmpty()){
                    edtNombres.setError("campo requerido");
                    edtCelular.setError("campo requerido");
                }else{
                    //registrar venta
                    String url = Util.RUTA + "registrar_venta.php?" +
                            "id_cliente="+id_cliente+
                            "&longitud=" +mLong+
                            "&latitud=" +mLat+
                            "&persona_recepcion=" +persona_recepcion+
                            "&celular=" +celular_recepcion+
                            "&total=" +precio_total+
                            "&id_producto=" +id+
                            "&cantidad=" +cantidad;

                    url = url.replace(" ","%20");
                    jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            ShowDialogBox();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegistrarCompraActivity.this, "error registro de compra", Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                }



                //registrar det_venta


                //actualizar stock producto
            }
        });

    }

    private void ShowDialogBox() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogoregexit);

        Button btnAceptarRegistroExitoso = dialog.findViewById(R.id.btnAceptarRegistroExitoso);

        Generos.initGeneros();



        btnAceptarRegistroExitoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrarCompraActivity.this, MainActivity.class);
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

    private void recuperarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombres","");
        String apPat = preferences.getString("apPaterno","");
        String apMat = preferences.getString("apMaterno","");
        edtCelular.setText(preferences.getString("celular",""));
        edtNombres.setText(apPat +" "+apMat+" "+nombre);
        //id_cliente = preferences.getInt("id",0);

    }
}