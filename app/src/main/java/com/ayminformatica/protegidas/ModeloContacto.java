package com.ayminformatica.protegidas;

public class ModeloContacto {
    private int id;
    private String nombre;
    private String fono;

    public ModeloContacto(int id, String nombre, String fono) {
        this.id = id;
        this.nombre = nombre;
        this.fono = fono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }
}
