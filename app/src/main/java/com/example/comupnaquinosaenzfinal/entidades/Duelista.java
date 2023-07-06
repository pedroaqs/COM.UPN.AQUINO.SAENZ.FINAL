package com.example.comupnaquinosaenzfinal.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "duelistas")
public class Duelista {

    @PrimaryKey(autoGenerate = true)
    private int idAplicacion;
    private int id;
    private String nombre;

    // Constructor
    public Duelista() {
    }

    public Duelista(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(int idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
