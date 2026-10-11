package modelos;

import java.time.LocalDate;

public class SocioTemporal extends Socio {

    private LocalDate fechaFinSuscripcion;

    public SocioTemporal(String id, String nombre, Plan plan, double cuotaBase, LocalDate fechaFinSuscripcion) {
        super(id, nombre, plan, cuotaBase);
        this.fechaFinSuscripcion = fechaFinSuscripcion;
    }

    public LocalDate getFechaFinSuscripcion() {
        return fechaFinSuscripcion;
    }

    @Override
    public double calcularCuota() {
        return getCuotaBase() * 1.10;
    }

    @Override
    public String getTipo() {
        return "TEMPORAL";
    }

    @Override
    public String getExtra() {
        return fechaFinSuscripcion.toString();
    }
}