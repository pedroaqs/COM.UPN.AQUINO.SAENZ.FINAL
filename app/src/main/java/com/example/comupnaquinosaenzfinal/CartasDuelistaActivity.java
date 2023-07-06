package com.example.comupnaquinosaenzfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.comupnaquinosaenzfinal.herramientas.AccesoRed;
import com.example.comupnaquinosaenzfinal.repository.CartaRepository;

public class CartasDuelistaActivity extends AppCompatActivity {

    TextView tv_duelista;
    Button btn_nueva_carta;
    Button btn_ver_mpa;
    RecyclerView rv_cartas_duelista;
    CartaRepository cartaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas_duelista);

        tv_duelista = findViewById(R.id.tv_duelista);
        btn_nueva_carta = findViewById(R.id.btn_nueva_carta);
        btn_ver_mpa = findViewById(R.id.btn_ver_mpa);
        rv_cartas_duelista = findViewById(R.id.rv_cartas_duelista);

        cargarData();
    }

    public void cargarData() {
        if(!AccesoRed.isNetworkConnected(CartasDuelistaActivity.this)) {

        } else {
            sincronizarDatos();
        }
    }

    public void  sincronizarDatos() {

    }

    public void listCartasDuelista() {

    }

}