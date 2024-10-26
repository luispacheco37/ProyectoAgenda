package com.example.miagendapersonal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTareas = findViewById(R.id.btnTareas);
        Button btnContactos = findViewById(R.id.btnContactos);
        Button btnEventos = findViewById(R.id.btnEventos);

        // Ir a pantalla de Tareas
        btnTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TareasActivity.class);
                startActivity(intent);
            }
        });

        // Transición a la pantalla de Contactos
        btnContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactosActivity.class);
                startActivity(intent);
            }
        });

        // Transición a la pantalla de Eventos (Placeholder)
        btnEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventosActivity.class);
                startActivity(intent);
            }
        });
    }
}
