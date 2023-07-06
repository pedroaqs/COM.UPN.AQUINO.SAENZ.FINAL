package com.example.comupnaquinosaenzfinal.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupnaquinosaenzfinal.CartasDuelistaActivity;
import com.example.comupnaquinosaenzfinal.R;
import com.example.comupnaquinosaenzfinal.entidades.Duelista;
import com.google.gson.Gson;

import java.util.List;

public class DuelistaAdapter extends RecyclerView.Adapter {
    List<Duelista> duelistas;
    public DuelistaAdapter(List<Duelista> duelistas) {
        this.duelistas = duelistas;
    }

    public void setDuelistas(List<Duelista> duelistas) {
        this.duelistas = duelistas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_duelista, parent, false);
        DuelistaViewHolder viewHolder = new DuelistaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Duelista duelista = duelistas.get(position);
        View view = holder.itemView;
        TextView duelista_tv_nombre = view.findViewById(R.id.duelista_tv_nombre);
        duelista_tv_nombre.setText(duelista.getNombre());

        view.setOnClickListener(view1 -> {
            Intent intent= new Intent(view.getContext(), CartasDuelistaActivity.class);
            String duelistajson = new Gson().toJson(duelista);
            intent.putExtra("Duelista",duelistajson);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return duelistas.size();
    }

    public class  DuelistaViewHolder extends RecyclerView.ViewHolder {

        public DuelistaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
