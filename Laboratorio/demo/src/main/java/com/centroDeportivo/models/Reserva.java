package com.centroDeportivo.models;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private int idUsuario;
    private int idActividad;
    private LocalDate fechaReserva;

    public Reserva() {}

    public Reserva(int id, int idUsuario, int idActividad, LocalDate fechaReserva) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
        this.fechaReserva = fechaReserva;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }
    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }
}
