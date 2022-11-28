package acevedo.EvalFin.org.Mediapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import acevedo.EvalFin.org.Clases.Documentos;
import acevedo.EvalFin.org.Clases.Generos;
import acevedo.EvalFin.org.Clases.Meses;
import acevedo.EvalFin.org.R;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MiPerfil extends AppCompatActivity {

    ImageView imgCliente,imgTomarFoto;
    EditText edtNombres, edtApPaterno, edtApMaterno, edtCorreo, edtCelular, edtNumDocumento;
    Button btnGenero, btnFecha, btnDocumento, btnGuardar;
    int gen,doc;
    String id;

    // foto
    private static final String CARPETA_PRINCIPAL = "misImagenesApp/";//directorio principal
    private static final String CARPETA_IMAGEN = "imagenes/";//directorio donde se guarda las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//directorio donde se guarda las fotos
    private String path; //almacena la ruta e la imagen
    File fileImagen;
    Bitmap bitmap;

    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;
    //

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
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

        int user = getIntent().getIntExtra("idUser",0);

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
//        File imagenArchivo = null;
//
//        try {
//            imagenArchivo= crearimagen();
//        }catch (IOException ex){
//            Log.e("error",ex.toString());
//        }
//        if (imagenArchivo != null){
//            Uri fotoUri = FileProvider.getUriForFile(this,"acevedo.EvalFin.org.fileprovider",imagenArchivo);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
//            startActivityForResult(intent,COD_FOTO);
//        }

//        File mFile = new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
//        boolean isCreada = mFile.exists();
//
//        if(isCreada == false){
//            isCreada = mFile.mkdirs();
//        }
//
//        if(isCreada == true){
//            Long consecutivo = System.currentTimeMillis()/1000; // captura la fecha y hora donde inicia el proceso
//            String nombre = consecutivo.toString()+".jpg";
//
//            path = Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN+File.separator+nombre; //ruta de almacenamiento
//
//            fileImagen = new File(path);
//
//            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            Uri fotoUri = FileProvider.getUriForFile(this,"acevedo.EvalFin.org.fileprovider",fileImagen);
//            //i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));
//            i.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
//
//            startActivityForResult(i,COD_FOTO);
//        }
    }

    private File crearimagen() throws IOException {
        Long consecutivo = System.currentTimeMillis()/1000; // captura la fecha y hora donde inicia el proceso
        String nombreImagen = consecutivo.toString()+".jpg";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen,".jpg",directorio);

        //rutaimagen = imagen.getAbsolutePath();
        return imagen;
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
//                Bitmap imgBitmap = BitmapFactory.decodeFile(rutaimagen);
//                bitmap = BitmapFactory.decodeFile(path);
//                imgCliente.setImageBitmap(bitmap);
                break;
        }
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