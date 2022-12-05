package acevedo.EvalFin.org.Mediapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import acevedo.EvalFin.org.R;
import acevedo.EvalFin.org.databinding.ActivityUbiClienteBinding;

public class ubiClienteActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityUbiClienteBinding binding;

    Button btnContinuar;
    double mLat, mLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUbiClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //inicializar variables
        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setVisibility(View.GONE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = getIntent().getIntExtra("id_producto",0);
                int stock = getIntent().getIntExtra("stock",0);
                String nombre = getIntent().getStringExtra("nombre");
                Double precio_venta = getIntent().getDoubleExtra("precio_venta",0.0);
                int cantidad = getIntent().getIntExtra("cantidad",0);
                String precio_total = getIntent().getStringExtra("precio_total");

                Intent i = new Intent(ubiClienteActivity.this,RegistrarCompraActivity.class);
                i.putExtra("latitud", mLat);
                i.putExtra("longitud", mLong);
                i.putExtra("id_producto",id);
                i.putExtra("stock",stock);
                i.putExtra("nombre",nombre);
                i.putExtra("precio_venta",precio_venta);
                i.putExtra("cantidad",cantidad);
                i.putExtra("precio_total",precio_total);
                startActivity(i);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng TiendaUbicacion = new LatLng(-12.068594018174071, -75.21028105169535);
        //validar permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.getUiSettings().setZoomControlsEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
        }
        else{
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "Dio permiso para utilizar su ubicacion", Toast.LENGTH_SHORT).show();
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        //fin validad permisos
        mMap.addMarker(new MarkerOptions().position(TiendaUbicacion).title("Universidad Continental"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TiendaUbicacion));
        CameraPosition cameraPosition=CameraPosition.builder().target(TiendaUbicacion).zoom(18).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull @NotNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.point))
                        .title("Ubicación")
                        .snippet("Mi Hogar")
                        .position(latLng)
                        .anchor(0.5F, 0.5f)
                );
                mLat = latLng.latitude;
                mLong = latLng.longitude;
                if(mLat != mLong){
                    btnContinuar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}