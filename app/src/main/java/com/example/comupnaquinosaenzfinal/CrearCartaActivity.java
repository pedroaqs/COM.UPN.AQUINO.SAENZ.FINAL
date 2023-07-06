package com.example.comupnaquinosaenzfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.comupnaquinosaenzfinal.entidades.Duelista;
import com.google.gson.Gson;

public class CrearCartaActivity extends AppCompatActivity {


    Duelista duelista;

    EditText et_nueva_carta_nombre;
    EditText et_nueva_carta_puntosataque;
    EditText et_nueva_carta_puntos_defensa;
    Button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carta);
        String duelistaJson = getIntent().getStringExtra("Duelista");
        duelista = new Gson().fromJson(duelistaJson, Duelista.class);
    }
}