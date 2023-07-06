package com.example.comupnaquinosaenzfinal.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupnaquinosaenzfinal.CartasDuelistaActivity;
import com.example.comupnaquinosaenzfinal.DetlleCartaActivity;
import com.example.comupnaquinosaenzfinal.R;
import com.example.comupnaquinosaenzfinal.entidades.Carta;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;
import com.google.gson.Gson;

import java.util.List;

public class CartasAdapter  extends RecyclerView.Adapter {
    List<Carta> cartas;
    public CartasAdapter(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_carta, parent, false);
        CartasAdapter.CartaViewHolder viewHolder = new CartasAdapter.CartaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Carta carta = cartas.get(position);
        View view = holder.itemView;
        TextView tv_nombre_carta = view.findViewById(R.id.tv_nombre_carta);
        tv_nombre_carta.setText(carta.getNombre());
        view.setOnClickListener(view1 -> {
            Intent intent= new Intent(view.getContext(), DetlleCartaActivity.class);
            String cartaJson = new Gson().toJson(carta);
            intent.putExtra("Carta",cartaJson);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public class  CartaViewHolder extends RecyclerView.ViewHolder {

        public CartaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}