package modelos;

import interfaces.Descontable;

public class SocioBase extends Socio implements Descontable {

    private int mesesAntiguedad;

    public SocioBase(String id, String nombre, Plan plan, double cuotaBase, int mesesAntiguedad) {
        super(id, nombre, plan, cuotaBase);
        this.mesesAntiguedad = mesesAntiguedad;
    }

    public int getMesesAntiguedad() {
        return mesesAntiguedad;
    }

    @Override
    public double calcularCuota() {
        return getCuotaBase() - (2 * mesesAntiguedad);
    }

    @Override
    public double calcularDescuento() {
        return getCuotaBase() * 0.05;
    }

    @Override
    public String getTipo() {
        return "BASE";
    }

    @Override
    public String getExtra() {
        return String.valueOf(mesesAntiguedad);
    }
}