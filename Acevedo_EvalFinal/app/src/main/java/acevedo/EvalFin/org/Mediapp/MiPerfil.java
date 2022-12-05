package acevedo.EvalFin.org.Mediapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import acevedo.EvalFin.org.Clases.Documentos;
import acevedo.EvalFin.org.Clases.Generos;
import acevedo.EvalFin.org.Clases.Meses;
import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.Util.Util;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MiPerfil extends AppCompatActivity {

    ImageView imgCliente,imgTomarFoto;
    EditText edtNombres, edtApPaterno, edtApMaterno, edtCorreo, edtCelular, edtNumDocumento;
    Button btnGenero, btnFecha, btnDocumento, btnGuardar;
    LinearLayout lyRegresar;
    int gen,doc;
    String id;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    int user=1;
    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        requestQueue = Volley.newRequestQueue(this);
        imgCliente = findViewById(R.id.imgCliente);
        edtApPaterno = findViewById(R.id.edtApPaterno);
        edtNombres = findViewById(R.id.edtNombres);
        edtApMaterno = findViewById(R.id.edtApMaterno);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtCelular = findViewById(R.id.edtCelular);
        edtNumDocumento = findViewById(R.id.edtNumDocumento);
        btnGenero = findViewById(R.id.btnGenero);
        btnFecha = findViewById(R.id.btnFecha);
        btnDocumento = findViewById(R.id.btnDocumento);
        btnGuardar = findViewById(R.id.btnGuardar);
        imgTomarFoto = findViewById(R.id.imgTomarFoto);
        lyRegresar = findViewById(R.id.lyRegresar);

        lyRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

         user = getIntent().getIntExtra("idUser",0);

        if(user == 2 ){
            cargarPreferenciasRepartidor();
        }else{
            cargarPreferencias();
        }


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
        btnDocumento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDocumento();
            }
        });

        if(validarPermisos()){
            imgTomarFoto.setEnabled(true);
        }else{
            imgTomarFoto.setEnabled(false);
        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
            }
        });

        imgTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarOpciones();
            }
        });
    }

    private boolean validarPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if ((shouldShowRequestPermissionRationale(CAMERA)) || (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA},100);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults.length==2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                imgTomarFoto.setEnabled(true);
            }else{
                solicitarPermisosManual();
            }
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones = {"si","no"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Desea configurar los persmisos de forma manual?");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(opciones[i].equals("si")){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package",getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(MiPerfil.this, "Los permisos no fueron aceptados", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MiPerfil.this);
        dialog.setTitle("Permisos Desativados");
        dialog.setMessage("Debe aceptar los persmisos para el correcto funcionamiento de la App");

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA},100);
            }
        });
        dialog.show();
    }

    private void mostrarOpciones() {
        final CharSequence[] opciones = {"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Elige una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(opciones[i].equals("Tomar Foto")){
                    abrirCamara();
                }else{
                    if(opciones[i].equals("Elegir de Galeria")){
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();


    }

    private void abrirCamara() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,COD_FOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCIONA:
                Uri mPath = data.getData();
                imgCliente.setImageURI(mPath);
                break;
            case COD_FOTO:
                Bundle extras = data.getExtras();
                Bitmap imgBitmap = (Bitmap) extras.get("data");
                imgCliente.setImageBitmap(imgBitmap);
                break;
        }
    }

    private void actualizarDatos(){
        Bitmap bitmap =  ((BitmapDrawable) imgCliente.getDrawable()).getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);


        //subir datos


        String nombres = edtNombres.getText().toString();
        String apPaterno = edtApPaterno.getText().toString();
        String apMaterno = edtApMaterno.getText().toString();
        String email = edtCorreo.getText().toString();
        String celular = edtCelular.getText().toString();
        String f_nacimiento = btnFecha.getText().toString();
        String documento = edtNumDocumento.getText().toString();
        String url = Util.RUTA+"actualizar_usuario.php";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MiPerfil.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                String imagen = Util.RUTA+"imagenes/usuarios/"+documento+".jpg";
                if(user == 2){
                    SharedPreferences preferences = getSharedPreferences("usuarioLoginRepartidor", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("correo",email);
                    editor.putString("documento",documento);
                    editor.putString("nombres",nombres);
                    editor.putString("apPaterno",apPaterno);
                    editor.putString("apMaterno",apMaterno);
                    editor.putString("celular",celular);
                    editor.putString("f_nacimiento",f_nacimiento);
                    editor.putInt("genero",gen);
                    editor.putInt("tipo_documento",doc);
                    editor.putString("img_url",imagen);
                    editor.commit();
                }else{
                    SharedPreferences preferences = getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("correo",email);
                    editor.putString("documento",documento);
                    editor.putString("nombres",nombres);
                    editor.putString("apPaterno",apPaterno);
                    editor.putString("apMaterno",apMaterno);
                    editor.putString("celular",celular);
                    editor.putString("f_nacimiento",f_nacimiento);
                    editor.putInt("genero",gen);
                    editor.putInt("tipo_documento",doc);
                    editor.putString("img_url",imagen);
                    editor.commit();
                }
                onBackPressed();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MiPerfil.this, "error BD", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<>();
                parametros.put("id",id);
                parametros.put("nombres",nombres);
                parametros.put("apPaterno",apPaterno);
                parametros.put("apMaterno",apMaterno);
                parametros.put("celular",celular);
                parametros.put("f_nacimiento",f_nacimiento);
                parametros.put("t_documento",doc+"");
                parametros.put("email",email);
                parametros.put("numDocumento",documento);
                parametros.put("genero",gen+"");
                parametros.put("imagen",fotoEnBase64);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);


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

        documento.setValue(doc);
        btnDocumento.setText(Documentos.getDocumentosArrayList().get(doc).getDocumento());


        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        genero.setValue(gen);

        btnGenero.setText(Generos.getGenerosArrayList().get(gen).getGenero());

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gen = genero.getValue();

                if(gen > -1){
                    btnGenero.setText(Generos.getGenerosArrayList().get(gen).getGenero());
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

    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("usuarioLogin", Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombres","");
        String apPat = preferences.getString("apPaterno","");
        String apMat = preferences.getString("apMaterno","");
        String url_img = preferences.getString("img_url","https://d1bvpoagx8hqbg.cloudfront.net/259/0f326ce8a41915e8b1d21ffaee087fae.jpg");
        String cel = preferences.getString("celular","");
        String fecha = preferences.getString("f_nacimiento","");
        gen = preferences.getInt("genero",0);
        doc = preferences.getInt("tipo_documento",0);
        String numDoc = preferences.getString("documento","");
        String correo = preferences.getString("correo","");
        id = preferences.getString("id","0");


        Glide.with(imgCliente).load(url_img).centerCrop().into(imgCliente);
        edtNombres.setText(nombre);
        edtApPaterno.setText(apPat);
        edtApMaterno.setText(apMat);
        edtCorreo.setText(correo);
        edtCelular.setText(cel);
        edtNumDocumento.setText(numDoc);

        btnFecha.setText(fecha);

    }
    private void cargarPreferenciasRepartidor() {
        SharedPreferences preferences = getSharedPreferences("usuarioLoginRepartidor", Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombres","");
        String apPat = preferences.getString("apPaterno","");
        String apMat = preferences.getString("apMaterno","");
        String url_img = preferences.getString("img_url","https://d1bvpoagx8hqbg.cloudfront.net/259/0f326ce8a41915e8b1d21ffaee087fae.jpg");
        String cel = preferences.getString("celular","");
        String fecha = preferences.getString("f_nacimiento","");
        gen = preferences.getInt("genero",0);
        doc = preferences.getInt("tipo_documento",0);
        String numDoc = preferences.getString("documento","");
        String correo = preferences.getString("correo","");
        id = preferences.getString("id","0");


        Glide.with(imgCliente).load(url_img).centerCrop().into(imgCliente);
        edtNombres.setText(nombre);
        edtApPaterno.setText(apPat);
        edtApMaterno.setText(apMat);
        edtCorreo.setText(correo);
        edtCelular.setText(cel);
        edtNumDocumento.setText(numDoc);

        btnFecha.setText(fecha);

    }
}