package com.example.comupnaquinosaenzfinal.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cartas")
public class Carta {
    @PrimaryKey(autoGenerate = true)
    private int id_aplicacion;
    private int id;
    private String nombre;
    private int puntosAtaque;
    private int puntosDefensa;
    private String imagen;
    private double latitud;
    private double longitud;
    private int id_aplicacion_duelista;

    // Constructor


    public Carta() {
    }

    public Carta( int id, String nombre, int puntosAtaque, int puntosDefensa, String imagen, double latitud, double longitud, int id_aplicacion_duelista) {
        this.id = id;
        this.nombre = nombre;
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.id_aplicacion_duelista = id_aplicacion_duelista;
    }

    // Getters y setters

    public int getId_aplicacion() {
        return id_aplicacion;
    }

    public void setId_aplicacion(int id_aplicacion) {
        this.id_aplicacion = id_aplicacion;
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

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public int getPuntosDefensa() {
        return puntosDefensa;
    }

    public void setPuntosDefensa(int puntosDefensa) {
        this.puntosDefensa = puntosDefensa;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getId_aplicacion_duelista() {
        return id_aplicacion_duelista;
    }

    public void setId_aplicacion_duelista(int id_aplicacion_duelista) {
        this.id_aplicacion_duelista = id_aplicacion_duelista;
    }
}
