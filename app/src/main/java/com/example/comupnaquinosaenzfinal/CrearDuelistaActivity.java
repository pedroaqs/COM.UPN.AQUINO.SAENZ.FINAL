package com.example.comupnaquinosaenzfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comupnaquinosaenzfinal.entidades.Duelista;
import com.example.comupnaquinosaenzfinal.repository.DuelistaRepository;

public class CrearDuelistaActivity extends AppCompatActivity {

    Button btn_crear_duelista;
    EditText ed_nuevoduelista_nombre;

    DuelistaRepository duelistaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_duelista);

        duelistaRepository = new DuelistaRepository(CrearDuelistaActivity.this);

        ed_nuevoduelista_nombre = findViewById(R.id.ed_nuevoduelista_nombre);
        btn_crear_duelista = findViewById(R.id.btn_crear_duelista);

        btn_crear_duelista.setOnClickListener(view -> {
            crearDuelista();
        });
    }

    public void crearDuelista() {
        String nombre = ed_nuevoduelista_nombre.getText().toString();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Duelista duelista = new Duelista();
        duelista.setNombre(nombre);
        duelistaRepository.insertDuelista(duelista);
        finish();
    }
}