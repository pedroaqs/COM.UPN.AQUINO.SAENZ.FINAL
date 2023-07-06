package com.example.comupnaquinosaenzfinal.repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.comupnaquinosaenzfinal.bd.AppDatabase;
import com.example.comupnaquinosaenzfinal.daos.DuelistaDao;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DuelistaRepository {
    private DuelistaDao duelistasDao;

    public DuelistaRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        duelistasDao = database.duelistaDao();
    }

    public List<Duelista> getAllDuelistas() {
        try {
            return new AsyncTask<Void, Void, List<Duelista>>() {
                @Override
                protected List<Duelista> doInBackground(Void... voids) {
                    return duelistasDao.getAllDuelistas();
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Duelista> searchDuelistasByName(String nombre) {

        return duelistasDao.searchDuelistasByName(nombre);
    }

    public void insertDuelista(Duelista duelista) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                duelistasDao.insertDuelista(duelista);
                return null;
            }
        }.execute();
    }
}
