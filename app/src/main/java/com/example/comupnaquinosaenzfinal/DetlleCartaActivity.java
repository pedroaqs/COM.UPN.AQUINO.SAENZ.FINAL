package com.example.comupnaquinosaenzfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comupnaquinosaenzfinal.entidades.Carta;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;

public class DetlleCartaActivity extends AppCompatActivity {

    ImageView tv_detalle_imagen_carta;
    TextView tv_detalle_nombre_carta;
    TextView tv_detalle_puosatque;
    TextView tv_detalle_puntos_defensa;
    TextView tv_detal_latitud;
    TextView tv_detalle_longitud;
    TextView tv_duelista_id;

    Carta carta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detlle_carta);

        String cartaJson = getIntent().getStringExtra("Carta");
        carta = new Gson().fromJson(cartaJson, Carta.class);

        tv_detalle_imagen_carta = findViewById(R.id.tv_detalle_imagen_carta);
        tv_detalle_nombre_carta = findViewById(R.id.tv_detalle_nombre_carta);
        tv_detalle_puosatque = findViewById(R.id.tv_detalle_puosatque);
        tv_detalle_puntos_defensa = findViewById(R.id.tv_detalle_puntos_defensa);
        tv_detal_latitud = findViewById(R.id.tv_detal_latitud);
        tv_detalle_longitud = findViewById(R.id.tv_detalle_longitud);
        tv_duelista_id = findViewById(R.id.tv_duelista_id);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // El permiso no ha sido concedido, o se ha revocado previamente.
            // Debes solicitarlo al usuario.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Muestra un diálogo o mensaje explicando por qué necesitas el permiso.
                // Luego, solicita el permiso utilizando ActivityCompat.requestPermissions().
            } else {
                // No es necesario mostrar una explicación adicional.
                // Solicita el permiso directamente.
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
            }
        } else {
            // El permiso ya ha sido concedido, puedes acceder al almacenamiento externo.
            // Realiza la operación de acceso al almacenamiento externo aquí.
        }
        if (carta.getImagen() != null) {
            Uri imageUri = Uri.parse("file://" + carta.getImagen());
            File imageFile = new File(carta.getImagen());

            if (imageFile.exists()) {
                // El archivo de imagen existe
                // Continúa con la carga utilizando Picasso
                Log.i("Cartaaaaaaaaaaaa existe", carta.getImagen());
                Picasso.get().load(imageFile).into(tv_detalle_imagen_carta);
                Log.i("Cartaaaaaaaaaaaa caargado", carta.getImagen());

                Bitmap bitmap = BitmapFactory.decodeFile(carta.getImagen());
                tv_detalle_imagen_carta.setImageBitmap(bitmap);
            } else {
                Log.i("Cartaaaaaaaaaaaa no existe", carta.getImagen());
                // El archivo de imagen no existe, maneja el caso de error
            }
            //Picasso.get()
              //      .load(new File(carta.getImagen()))
                //    .into(tv_detalle_imagen_carta);
        }
        tv_detalle_nombre_carta.setText(carta.getNombre());
        tv_detalle_puosatque.setText(""+carta.getPuntosAtaque());
        tv_detalle_puntos_defensa.setText(""+carta.getPuntosDefensa());
        tv_detal_latitud.setText(""+carta.getLatitud());
        tv_detalle_longitud.setText(""+carta.getLongitud());
        tv_duelista_id.setText(""+carta.getId_aplicacion_duelista());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El permiso ha sido concedido, puedes acceder al almacenamiento externo.
                // Realiza la operación de acceso al almacenamiento externo aquí.
                if (carta.getImagen() != null) {
                    Uri imageUri = Uri.parse("file://" + carta.getImagen());
                    File imageFile = new File(carta.getImagen());

                    if (imageFile.exists()) {
                        // El archivo de imagen existe
                        // Continúa con la carga utilizando Picasso
                        Log.i("Cartaaaaaaaaaaaa existe", carta.getImagen());
                        Picasso.get().load(imageFile).into(tv_detalle_imagen_carta);
                        Log.i("Cartaaaaaaaaaaaa caargado", carta.getImagen());

                        Bitmap bitmap = BitmapFactory.decodeFile(carta.getImagen());
                        tv_detalle_imagen_carta.setImageBitmap(bitmap);
                    } else {
                        Log.i("Cartaaaaaaaaaaaa no existe", carta.getImagen());
                        // El archivo de imagen no existe, maneja el caso de error
                    }
                    //Picasso.get()
                    //      .load(new File(carta.getImagen()))
                    //    .into(tv_detalle_imagen_carta);
                }
            } else {
                // El permiso ha sido denegado, debes manejar esta situación.
                // Por ejemplo, muestra un mensaje al usuario indicando que no se puede acceder al almacenamiento sin el permiso.
            }
        }
    }
}