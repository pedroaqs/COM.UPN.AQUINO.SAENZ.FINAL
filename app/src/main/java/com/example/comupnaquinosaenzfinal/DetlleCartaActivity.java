package com.example.comupnaquinosaenzfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        if (carta.getImagen() != null) {
            Picasso.get()
                    .load(new File(carta.getImagen()))
                    .into(tv_detalle_imagen_carta);
        }
        Toast.makeText(this, ""+carta.getImagen(), Toast.LENGTH_SHORT).show();
        tv_detalle_nombre_carta.setText(carta.getNombre());
        tv_detalle_puosatque.setText(""+carta.getPuntosAtaque());
        tv_detalle_puntos_defensa.setText(""+carta.getPuntosDefensa());
        tv_detal_latitud.setText(""+carta.getLatitud());
        tv_detalle_longitud.setText(""+carta.getLongitud());
        tv_duelista_id.setText(""+carta.getId_aplicacion_duelista());

    }
}