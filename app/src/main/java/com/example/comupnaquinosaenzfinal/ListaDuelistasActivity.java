package com.example.comupnaquinosaenzfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.comupnaquinosaenzfinal.adapters.DuelistaAdapter;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;
import com.example.comupnaquinosaenzfinal.herramientas.AccesoRed;
import com.example.comupnaquinosaenzfinal.repository.DuelistaRepository;

import java.util.ArrayList;
import java.util.List;

public class ListaDuelistasActivity extends AppCompatActivity {

    RecyclerView rv_duelistas;
    DuelistaAdapter duelistaAdapter;
    DuelistaRepository duelistaRepository;
    Button btn_duelistas_crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_duelistas);
        duelistaRepository =new DuelistaRepository(ListaDuelistasActivity.this);
        rv_duelistas = findViewById(R.id.rv_duelistas);
        rv_duelistas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        duelistaAdapter = new DuelistaAdapter(new ArrayList<Duelista>());
        rv_duelistas.setAdapter(duelistaAdapter);

        // crear Duelista
        btn_duelistas_crear = findViewById(R.id.btn_duelistas_crear);
        btn_duelistas_crear.setOnClickListener(view -> {
            Intent intent = new Intent(ListaDuelistasActivity.this,CrearDuelistaActivity.class);
            startActivity(intent);
        });

        cargarData();
    }

    public void  cargarData() {
        if (!AccesoRed.isNetworkConnected(ListaDuelistasActivity.this)) {
            listaDuelistas();
        } else {
            sincronizarDatos();
        }
    }

    public void sincronizarDatos() {
        listaDuelistas();
    }

    public void listaDuelistas() {
        List<Duelista> duelistasbd = duelistaRepository.getAllDuelistas();
        duelistaAdapter.setDuelistas(duelistasbd);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Cargar los duelistas en el onResume()
        Toast.makeText(this, "Ejecutndo onResume", Toast.LENGTH_SHORT).show();
        cargarData();
    }


}