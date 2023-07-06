package com.example.comupnaquinosaenzfinal.repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.comupnaquinosaenzfinal.bd.AppDatabase;
import com.example.comupnaquinosaenzfinal.daos.CartaDao;
import com.example.comupnaquinosaenzfinal.entidades.Carta;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartaRepository {
    private CartaDao cartaDao;

    public CartaRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        cartaDao = database.cartaDao();
    }

    public List<Carta> getCartasByDuelistaId(int idAplicacionDuelista) {
        try {
            return new AsyncTask<Void, Void, List<Carta>>() {
                @Override
                protected List<Carta> doInBackground(Void... voids) {
                    return cartaDao.getCartasByDuelistaId(idAplicacionDuelista);
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Carta> searchCartasByNameAndDuelistaId(String nombre, int idAplicacionDuelista) {
        return cartaDao.searchCartasByNameAndDuelistaId(nombre, idAplicacionDuelista);
    }

    public void insertCarta(Carta carta) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                cartaDao.insertCarta(carta);
                return null;
            }
        }.execute();
    }

    public Carta getCartaByIdAplicacion(int idAplicacion) {
        return cartaDao.getCartaByIdAplicacion(idAplicacion);
    }
}
