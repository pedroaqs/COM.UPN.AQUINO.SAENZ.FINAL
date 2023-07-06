package com.example.comupnaquinosaenzfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.comupnaquinosaenzfinal.entidades.Carta;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;
import com.example.comupnaquinosaenzfinal.repository.CartaRepository;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;

public class CrearCartaActivity extends AppCompatActivity {


    Duelista duelista;

    EditText et_nueva_carta_nombre;
    EditText et_nueva_carta_puntosataque;
    EditText et_nueva_carta_puntos_defensa;
    Button btn_galeria;
    Button btn_guardar;
    ImageView imageView;
    String rutaFoto;

    LocationManager mLocationManager;
    Location location;

    CartaRepository cartaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carta);
        String duelistaJson = getIntent().getStringExtra("Duelista");
        duelista = new Gson().fromJson(duelistaJson, Duelista.class);
        cartaRepository = new CartaRepository(CrearCartaActivity.this);

        et_nueva_carta_nombre = findViewById(R.id.et_nueva_carta_nombre);
        et_nueva_carta_puntosataque = findViewById(R.id.et_nueva_carta_puntosataque);
        et_nueva_carta_puntos_defensa = findViewById(R.id.et_nueva_carta_puntos_defensa);

        btn_galeria = findViewById(R.id.btn_galeria);
        btn_guardar = findViewById(R.id.btn_guardar);
        imageView = findViewById(R.id.imageView);

        btn_galeria.setOnClickListener(view -> {
            abrirGaleria();
        });

        btn_guardar.setOnClickListener(view -> {
            crear();
        });
    }

    public void crear() {
        String nombre = et_nueva_carta_nombre.getText().toString();
        int puntosataque = Integer.parseInt(et_nueva_carta_puntosataque.getText().toString());
        int puntosdefensa = Integer.parseInt(et_nueva_carta_puntos_defensa.getText().toString());
        if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            };
            requestPermissions(permissions, 3000);

        }
        else {
            // configurar frecuencia de actualización de GPS
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1, this);
            location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        double lat =  location == null ?  0 :   location.getLatitude();
        double lng = location == null ?  0 : location.getLongitude();

        if(nombre.isEmpty() || puntosataque == 0 || puntosdefensa == 0 || lat == 0 || lng == 0 || rutaFoto.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        Carta carta = new Carta();
        carta.setNombre(nombre);
        carta.setPuntosAtaque(puntosataque);
        carta.setPuntosDefensa(puntosdefensa);
        carta.setLatitud(lat);
        carta.setLongitud(lng);
        carta.setImagen(rutaFoto);
        carta.setId_aplicacion_duelista(duelista.getIdAplicacion());
        cartaRepository.insertCarta(carta);
        finish();


    }
    public void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1002);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1002 && resultCode == RESULT_OK) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close(); // close cursor

            Log.i("ImagenSIUUUU",picturePath);
            rutaFoto = String.valueOf(picturePath);

            if (data != null) {
                Uri imagenUri = data.getData();
                rutaFoto = obtenerRutaImagen(imagenUri);
                // Resto del código...
            } else {
                Toast.makeText(this, "Error al seleccionar la imagen", Toast.LENGTH_SHORT).show();
            }
        }

        if (rutaFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(rutaFoto);
            imageView.setImageBitmap(bitmap);
            //Picasso.get()
              //      .load(new File(rutaFoto))
                //    .into(imageView);
        }
    }

    private String obtenerRutaImagen(Uri uri) {
        String ruta = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            ruta = cursor.getString(columnIndex);
            cursor.close();
        }
        return ruta;
    }
}