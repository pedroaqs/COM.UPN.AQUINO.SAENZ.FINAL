package com.example.comupnaquinosaenzfinal.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.comupnaquinosaenzfinal.entidades.Duelista;

import java.util.List;

@Dao
public interface  DuelistaDao {

    @Query("SELECT * FROM duelistas")
    List<Duelista> getAllDuelistas();

    @Query("SELECT * FROM duelistas WHERE nombre LIKE '%' || :nombre || '%'")
    List<Duelista> searchDuelistasByName(String nombre);

    @Insert
    void insertDuelista(Duelista duelista);

}
