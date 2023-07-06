package com.example.comupnaquinosaenzfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.comupnaquinosaenzfinal.adapters.DuelistaAdapter;

public class ListaDuelistasActivity extends AppCompatActivity {

    RecyclerView rv_duelistas;
    DuelistaAdapter duelistaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_duelistas);
        rv_duelistas = findViewById(R.id.rv_duelistas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        PublicationAdapter publicationAdapter = new PublicationAdapter(response.body().items);
        recyclerView.setAdapter(publicationAdapter);
    }
}