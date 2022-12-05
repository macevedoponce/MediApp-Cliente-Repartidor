package acevedo.EvalFin.org.Mediapp;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.intellij.lang.annotations.Language;

import java.util.Calendar;

import acevedo.EvalFin.org.Clases.Generos;
import acevedo.EvalFin.org.Clases.Meses;
import acevedo.EvalFin.org.R;

public class MoreInfo extends AppCompatActivity {
    TextInputLayout tilNombres, tilPaterno,tilMaterno, tilCelular;
    EditText edtNombres, edtApPaterno, edtApMaterno, edtNumCelular;
    Button btnFecha, btnGenero, btnSiguiente;
    TextView txtVolver,txtRevisa;
    int genero_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        tilNombres = findViewById(R.id.tilNombres);
        tilPaterno = findViewById(R.id.tilApPaterno);
        tilMaterno = findViewById(R.id.tilApMaterno);
        tilCelular = findViewById(R.id.tilNumCelular);

        edtNombres = findViewById(R.id.edtNombres);
        edtApPaterno = findViewById(R.id.edtApPaterno);
        edtApMaterno = findViewById(R.id.edtApMaterno);
        edtNumCelular = findViewById(R.id.edtCelular);
        btnFecha = findViewById(R.id.btnFecha);
        btnGenero = findViewById(R.id.btnGenero);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtVolver = findViewById(R.id.txtVolver);
        txtRevisa = findViewById(R.id.txtRevisa);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                showDialogFecha(year,month,day);
            }
        });

        btnGenero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogGenero();
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = getIntent().getStringExtra("correo");
                String pass = getIntent().getStringExtra("password");
                String tipo_user = getIntent().getStringExtra("tipo_user");

                String nombres = edtNombres.getText().toString();
                String apPat = edtApPaterno.getText().toString();
                String apMat = edtApMaterno.getText().toString();
                String celular = edtNumCelular.getText().toString();
                String f_nacimiento = btnFecha.getText().toString();

                if(!nombres.isEmpty() && !apPat.isEmpty() && !apMat.isEmpty() && !celular.isEmpty()){
                    Intent i = new Intent(MoreInfo.this,DatosFinales.class);
                    i.putExtra("correo", correo);
                    i.putExtra("password", pass );
                    i.putExtra("tipo_user", tipo_user);
                    i.putExtra("nombres", nombres);
                    i.putExtra("apPat", apPat);
                    i.putExtra("apMat", apMat);
                    i.putExtra("celular", celular);
                    i.putExtra("f_nacimiento", f_nacimiento);
                    i.putExtra("genero", genero_id);
                    startActivity(i);
                }else{
                    txtRevisa.setText("Complete los espacios requeridos");
                    txtRevisa.setTextColor(Color.RED);
                }

            }
        });
        txtVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void showDialogGenero() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.generobslayout);

        NumberPicker genero = dialog.findViewById(R.id.genero);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAceptar = dialog.findViewById(R.id.btnAceptar);

        Generos.initGeneros();


        genero.setMaxValue(Generos.getGenerosArrayList().size()-1);
        genero.setDisplayedValues(Generos.generosNames());


        genero.setMinValue(0);


        genero.setValue(1);



        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                genero_id = genero.getValue();

                if(genero_id > -1){
                    btnGenero.setText(Generos.getGenerosArrayList().get(genero_id).getGenero());
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

    private void showDialogFecha(int year, int month, int day) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fechabslayout);

        NumberPicker dia = dialog.findViewById(R.id.day);
        NumberPicker mes = dialog.findViewById(R.id.month);
        NumberPicker anio = dialog.findViewById(R.id.year);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        Button btnAceptar = dialog.findViewById(R.id.btnAceptar);

        Meses.initMeses();

        dia.setMaxValue(31);
        mes.setMaxValue(Meses.getMesesArrayList().size()-1);
        mes.setDisplayedValues(Meses.mesesNames());
        anio.setMaxValue(year);

        dia.setMinValue(1);
        mes.setMinValue(0);
        anio.setMinValue(year-70);

        dia.setValue(day);
        mes.setValue(month+1);
        anio.setValue(year);


        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fechaSeleccionada;

                if(mes.getValue() != 0){
                    fechaSeleccionada = anio.getValue()+"-"+ mes.getValue()+"-"+dia.getValue();
                    btnFecha.setText(fechaSeleccionada);
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