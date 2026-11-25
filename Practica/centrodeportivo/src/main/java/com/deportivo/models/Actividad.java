package com.deportivo.models;

public class Actividad {
    private int id;
    private String nombre;
    private int plazasDisponibles;

    public Actividad() {}

    public Actividad(int id, String nombre, int plazasDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.plazasDisponibles = plazasDisponibles;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getPlazasDisponibles() { return plazasDisponibles; }
    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " - plazas: " + plazasDisponibles;
    }
}

