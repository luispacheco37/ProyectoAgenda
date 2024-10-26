package com.example.miagendapersonal;

import android.graphics.Bitmap;

public class Contacto {
    private String nombre;
    private String telefono;
    private Bitmap imagen; // Nueva propiedad para la imagen

    public Contacto(String nombre, String telefono, Bitmap imagen) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    // Nuevo setter para la imagen
    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
