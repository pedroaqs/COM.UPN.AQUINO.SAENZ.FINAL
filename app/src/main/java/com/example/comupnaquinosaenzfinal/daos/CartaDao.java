package com.example.comupnaquinosaenzfinal.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.comupnaquinosaenzfinal.entidades.Carta;

import java.util.List;

@Dao
public interface  CartaDao {

    @Query("SELECT * FROM cartas WHERE id_aplicacion_duelista = :idAplicacionDuelista")
    List<Carta> getCartasByDuelistaId(int idAplicacionDuelista);

    @Query("SELECT * FROM cartas WHERE nombre LIKE '%' || :nombre || '%' AND id_aplicacion_duelista = :idAplicacionDuelista")
    List<Carta> searchCartasByNameAndDuelistaId(String nombre, int idAplicacionDuelista);

    @Insert
    void insertCarta(Carta carta);

    @Query("SELECT * FROM cartas WHERE id_aplicacion = :id_aplicacion")
    Carta getCartaByIdAplicacion(int id_aplicacion);
}
