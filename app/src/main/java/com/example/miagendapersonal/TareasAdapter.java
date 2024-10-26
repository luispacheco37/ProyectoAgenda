package com.example.miagendapersonal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolder> {

    private ArrayList<Tarea> listaTareas;

    public TareasAdapter(ArrayList<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarea tarea = listaTareas.get(position);
        holder.textViewTarea.setText(tarea.getNombre());
        holder.checkBoxCompletada.setChecked(tarea.isCompletada());

        holder.checkBoxCompletada.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tarea.setCompletada(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTarea;
        CheckBox checkBoxCompletada;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTarea = itemView.findViewById(R.id.textViewTarea);
            checkBoxCompletada = itemView.findViewById(R.id.checkBoxCompletada);
        }
    }
}
