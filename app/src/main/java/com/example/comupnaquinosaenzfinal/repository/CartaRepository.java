package com.example.comupnaquinosaenzfinal.repository;

import android.content.Context;

import com.example.comupnaquinosaenzfinal.bd.AppDatabase;
import com.example.comupnaquinosaenzfinal.daos.CartaDao;
import com.example.comupnaquinosaenzfinal.entidades.Carta;

import java.util.List;

public class CartaRepository {
    private CartaDao cartaDao;

    public CartaRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        cartaDao = database.cartaDao();
    }

    public List<Carta> getCartasByDuelistaId(int idAplicacionDuelista) {
        return cartaDao.getCartasByDuelistaId(idAplicacionDuelista);
    }

    public List<Carta> searchCartasByNameAndDuelistaId(String nombre, int idAplicacionDuelista) {
        return cartaDao.searchCartasByNameAndDuelistaId(nombre, idAplicacionDuelista);
    }

    public void insertCarta(Carta carta) {
        cartaDao.insertCarta(carta);
    }

    public Carta getCartaByIdAplicacion(int idAplicacion) {
        return cartaDao.getCartaByIdAplicacion(idAplicacion);
    }
}
