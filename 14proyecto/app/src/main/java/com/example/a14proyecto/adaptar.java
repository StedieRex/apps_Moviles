package com.example.a14proyecto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a14proyecto.articulos;

import java.util.ArrayList;

public class adaptar extends RecyclerView.Adapter<adaptar.ViewHolderDatos> {
    ArrayList<articulos> lista;

    public adaptar(ArrayList<articulos> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public adaptar.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.intem_list, null, false);
        return new ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptar.ViewHolderDatos holder, int position) {
        holder.asignardato(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        EditText txtID;
        EditText txtNom;
        EditText txtCosto;
        EditText txtFecha;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.editTextText5);
            txtNom = itemView.findViewById(R.id.editTextText6);
            txtCosto = itemView.findViewById(R.id.editTextText8);
            txtFecha = itemView.findViewById(R.id.editTextText9);
        }

        public void asignardato(articulos a) {
            txtID.setText(a.id);
            txtNom.setText(a.nom);
            txtCosto.setText(a.costo);
            txtFecha.setText(a.fecha);
        }
    }
}
