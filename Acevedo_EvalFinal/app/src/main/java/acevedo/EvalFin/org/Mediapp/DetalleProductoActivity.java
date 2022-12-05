package acevedo.EvalFin.org.Mediapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import acevedo.EvalFin.org.Clases.Generos;
import acevedo.EvalFin.org.R;

public class DetalleProductoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgProductoFull;
    CardView cardMas, cardMenos;
    TextView txtCantidad,txtPresentacionFull,txtCodigoFull,txtNombreFull,txtPrecioFull,txtVerMas,txtDescripcion;
    Button btnComprar;
    LinearLayout lyRegresar;
    int cantidad=1;
    Double precio;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        cardMas = findViewById(R.id.cardMas);
        cardMenos = findViewById(R.id.cardMenos);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPresentacionFull = findViewById(R.id.txtPresentacionFull);
        txtCodigoFull = findViewById(R.id.txtCodigoFull);
        txtNombreFull = findViewById(R.id.txtNombreFull);
        txtPrecioFull = findViewById(R.id.txtPrecioFull);
        btnComprar = findViewById(R.id.btnComprar);
        txtVerMas = findViewById(R.id.txtVerMas);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        imgProductoFull = findViewById(R.id.imgProductoFull);
        lyRegresar = findViewById(R.id.lyRegresar);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        cargarDatos();

        lyRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = getIntent().getIntExtra("id",0);
                int id_categoria = getIntent().getIntExtra("id_categoria",0);
                int stock = getIntent().getIntExtra("stock",0);
                int estado = getIntent().getIntExtra("estado",0);
                String codigo = getIntent().getStringExtra("codigo");
                String nombre = getIntent().getStringExtra("nombre");
                String presentacion = getIntent().getStringExtra("presentacion");
                String ruta_imagen = getIntent().getStringExtra("ruta_imagen");
                String descripcion = getIntent().getStringExtra("descripcion");
                Double precio_venta = getIntent().getDoubleExtra("precio_venta",0.0);

                Intent i = new Intent(DetalleProductoActivity.this,ubiClienteActivity.class);
                i.putExtra("id_producto",id);
                i.putExtra("stock",stock);
                i.putExtra("nombre",nombre);
                i.putExtra("precio_venta",precio_venta);
                i.putExtra("cantidad",cantidad);
                i.putExtra("precio_total",txtPrecioFull.getText().toString());
                startActivity(i);

            }
        });

        txtVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verMas();
            }
        });


    }

    private void verMas() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.vermasbslayout);

        TextView txtDesc = dialog.findViewById(R.id.txtDescripcion);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAceptar = dialog.findViewById(R.id.btnAceptar);

        Generos.initGeneros();


        txtDesc.setText(txtDescripcion.getText().toString());

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {dialog.dismiss();
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

    private void cargarDatos() {
        //recepcion de datos

        int estado = getIntent().getIntExtra("estado",0);
        String codigo = getIntent().getStringExtra("codigo");
        String nombre = getIntent().getStringExtra("nombre");
        String presentacion = getIntent().getStringExtra("presentacion");
        String ruta_imagen = getIntent().getStringExtra("ruta_imagen");
        String descripcion = getIntent().getStringExtra("descripcion");
        Double precio_venta = getIntent().getDoubleExtra("precio_venta",0.0);
        //fin

        Glide.with(this).load(ruta_imagen).into(imgProductoFull);
        precio = precio_venta;
        txtPresentacionFull.setText(presentacion);
        txtCodigoFull.setText(codigo);
        txtNombreFull.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecioFull.setText(precio+"0");
        if(estado == 0){
            btnComprar.setEnabled(false);
            cardMenos.setEnabled(false);
            cardMas.setEnabled(false);
            txtCantidad.setText("0");
            btnComprar.setText("No tenemos stock");
        }else{
            txtCantidad.setText("1");
        }

        cardMas.setOnClickListener(this);

        cardMenos.setOnClickListener(this);

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(DetalleProductoActivity)
            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardMas:
                cantidad++;

                break;
            case R.id.cardMenos:
                cantidad--;
                break;
        }

        if(cantidad >= 1 && cantidad <=5){
            Double precio_final = obtenerDecimales( precio*cantidad);
            txtCantidad.setText(cantidad+"");
            vibrator.vibrate(200);
            txtPrecioFull.setText(precio_final+"0");
        }else{
            if (cantidad<=0){
                cantidad = 1;
                txtCantidad.setText(cantidad+"");
                vibrator.vibrate(400);
            }

            if (cantidad>=5){
                cantidad = 5;
                txtCantidad.setText(cantidad+"");
                vibrator.vibrate(400);
            }

        }
    }

    private Double obtenerDecimales(Double v) {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2); //Define 2 decimales.
        return Double.valueOf(format.format(v));
    }
}