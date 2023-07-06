package com.example.comupnaquinosaenzfinal.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.comupnaquinosaenzfinal.daos.CartaDao;
import com.example.comupnaquinosaenzfinal.daos.DuelistaDao;
import com.example.comupnaquinosaenzfinal.entidades.Carta;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;

@Database(entities = {Duelista.class, Carta.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "final_db";
    private static volatile AppDatabase instance;

    public abstract DuelistaDao duelistaDao();
    public abstract CartaDao cartaDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
