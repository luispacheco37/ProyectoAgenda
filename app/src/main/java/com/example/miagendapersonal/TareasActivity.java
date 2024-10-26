package com.example.miagendapersonal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TareasActivity extends AppCompatActivity {

    private ArrayList<Tarea> listaTareas;
    private TareasAdapter tareasAdapter;
    private EditText editTextNuevaTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        RecyclerView recyclerViewTareas = findViewById(R.id.recyclerViewTareas);
        editTextNuevaTarea = findViewById(R.id.editTextNuevaTarea);
        Button buttonAgregarTarea = findViewById(R.id.buttonAgregarTarea);
        Button buttonVolver = findViewById(R.id.buttonVolver);

        // Iniciar lista de tareas

        listaTareas = new ArrayList<>();

        // Configuracion del RecyclerView y el adaptador
        tareasAdapter = new TareasAdapter(listaTareas);
        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTareas.setAdapter(tareasAdapter);

        // nueva tarea
        buttonAgregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreTarea = editTextNuevaTarea.getText().toString();
                if (!nombreTarea.isEmpty()) {
                    listaTareas.add(new Tarea(nombreTarea, false));
                    tareasAdapter.notifyDataSetChanged();
                    editTextNuevaTarea.setText("");
                }
            }
        });

        // Funcion para regresar a MainActivity
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TareasActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
