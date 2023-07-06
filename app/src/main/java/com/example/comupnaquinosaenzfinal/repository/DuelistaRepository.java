package com.example.comupnaquinosaenzfinal.repository;

import android.content.Context;

import com.example.comupnaquinosaenzfinal.bd.AppDatabase;
import com.example.comupnaquinosaenzfinal.daos.DuelistaDao;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;

import java.util.List;

public class DuelistaRepository {
    private DuelistaDao duelistasDao;

    public DuelistaRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        duelistasDao = database.duelistaDao();
    }

    public List<Duelista> getAllDuelistas() {
        return duelistasDao.getAllDuelistas();
    }

    public List<Duelista> searchDuelistasByName(String nombre) {
        return duelistasDao.searchDuelistasByName(nombre);
    }

    public void insertDuelista(Duelista duelista) {
        duelistasDao.insertDuelista(duelista);
    }
}
