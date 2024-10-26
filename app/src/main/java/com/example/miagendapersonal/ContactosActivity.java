package com.example.miagendapersonal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.util.ArrayList;

public class ContactosActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private EditText editTextNombre, editTextTelefono;
    private Button buttonAgregarContacto, buttonSeleccionarImagen, buttonVolverPrincipal;
    private ImageView imageViewContacto;
    private RecyclerView recyclerViewContactos;
    private ContactosAdapter contactosAdapter;
    private ArrayList<Contacto> listaContactos;
    private Bitmap imagenSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        buttonAgregarContacto = findViewById(R.id.buttonAgregarContacto);
        buttonSeleccionarImagen = findViewById(R.id.buttonSeleccionarImagen);
        imageViewContacto = findViewById(R.id.imageViewContacto);
        buttonVolverPrincipal = findViewById(R.id.buttonVolverPrincipal);  // Botón Volver
        recyclerViewContactos = findViewById(R.id.recyclerViewContactos);

        listaContactos = new ArrayList<>();
        contactosAdapter = new ContactosAdapter(listaContactos);
        recyclerViewContactos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContactos.setAdapter(contactosAdapter);

        // Listener para seleccionar imagen
        buttonSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagen();
            }
        });

        // Listener para agregar contacto
        buttonAgregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                String telefono = editTextTelefono.getText().toString();

                if (!nombre.isEmpty() && !telefono.isEmpty() && imagenSeleccionada != null) {
                    Contacto nuevoContacto = new Contacto(nombre, telefono, imagenSeleccionada);
                    listaContactos.add(nuevoContacto);
                    contactosAdapter.notifyItemInserted(listaContactos.size() - 1);
                    editTextNombre.setText("");
                    editTextTelefono.setText("");
                    Toast.makeText(ContactosActivity.this, "Contacto agregado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContactosActivity.this, "Por favor, completa todos los campos e incluye una imagen", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener para volver a la MainActivity
        buttonVolverPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para regresar a MainActivity
                Intent intent = new Intent(ContactosActivity.this, MainActivity.class);
                startActivity(intent);  // Iniciar la MainActivity
                finish();  // Cerrar ContactosActivity para que no quede en la pila de actividades
            }
        });
    }

    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            new Thread(() -> {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    imagenSeleccionada = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                    runOnUiThread(() -> {
                        imageViewContacto.setImageBitmap(imagenSeleccionada);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
