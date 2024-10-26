package com.example.miagendapersonal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder> {

    private ArrayList<Contacto> listaContactos;

    public ContactosAdapter(ArrayList<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = listaContactos.get(position);
        holder.textViewNombre.setText(contacto.getNombre());
        holder.textViewTelefono.setText(contacto.getTelefono());
        holder.imageViewContacto.setImageBitmap(contacto.getImagen()); // Mostrar la imagen
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre, textViewTelefono;
        ImageView imageViewContacto; // Nuevo ImageView para mostrar la imagen

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewTelefono = itemView.findViewById(R.id.textViewTelefono);
            imageViewContacto = itemView.findViewById(R.id.imageViewContacto); // Enlazar el ImageView
        }
    }
}
