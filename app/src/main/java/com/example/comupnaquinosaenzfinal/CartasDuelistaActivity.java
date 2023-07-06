package com.example.comupnaquinosaenzfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comupnaquinosaenzfinal.adapters.CartasAdapter;
import com.example.comupnaquinosaenzfinal.entidades.Carta;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;
import com.example.comupnaquinosaenzfinal.herramientas.AccesoRed;
import com.example.comupnaquinosaenzfinal.repository.CartaRepository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartasDuelistaActivity extends AppCompatActivity {

    TextView tv_duelista;
    Button btn_nueva_carta;
    Button btn_ver_mpa;
    RecyclerView rv_cartas_duelista;
    CartasAdapter cartasAdapter;
    CartaRepository cartaRepository;
    Duelista duelista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas_duelista);

        String duelistaJson = getIntent().getStringExtra("Duelista");
        duelista = new Gson().fromJson(duelistaJson, Duelista.class);

        cartaRepository = new CartaRepository(CartasDuelistaActivity.this);

        tv_duelista = findViewById(R.id.tv_duelista);
        btn_nueva_carta = findViewById(R.id.btn_nueva_carta);
        btn_ver_mpa = findViewById(R.id.btn_ver_mpa);
        rv_cartas_duelista = findViewById(R.id.rv_cartas_duelista);

        tv_duelista.setText("Duelista "+duelista.getNombre());

        rv_cartas_duelista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cartasAdapter = new CartasAdapter(new ArrayList<Carta>());
        rv_cartas_duelista.setAdapter(cartasAdapter);

        // boton nuevo
        btn_nueva_carta.setOnClickListener(view -> {
            Intent intent = new Intent(CartasDuelistaActivity.this,CrearCartaActivity.class);
            String duelistajson = new Gson().toJson(duelista);
            intent.putExtra("Duelista",duelistajson);
            startActivity(intent);
        });
        // boton ver map
        btn_ver_mpa.setOnClickListener(view -> {

        });
        cargarData();
    }

    public void cargarData() {
        if(!AccesoRed.isNetworkConnected(CartasDuelistaActivity.this)) {
            listCartasDuelista();
        } else {
            sincronizarDatos();
        }
    }

    public void  sincronizarDatos() {
        listCartasDuelista();
    }

    public void listCartasDuelista() {
        List<Carta> cartasduelistabd = cartaRepository.getCartasByDuelistaId(duelista.getIdAplicacion());
        cartasAdapter.setCartas(cartasduelistabd);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // carga carts on resume
        Toast.makeText(this, "cargando data", Toast.LENGTH_SHORT).show();
        cargarData();
    }

}