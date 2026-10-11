package modelos;

public class SocioVIP extends SocioBase {

    private boolean accesoTaquilla;

    public SocioVIP(String id, String nombre, Plan plan, double cuotaBase, int mesesAntiguedad, boolean accesoTaquilla) {
        super(id, nombre, plan, cuotaBase, mesesAntiguedad);
        this.accesoTaquilla = accesoTaquilla;
    }

    public boolean isAccesoTaquilla() {
        return accesoTaquilla;
    }

    @Override
    public double calcularCuota() {
        return getCuotaBase() + 15 - calcularDescuento();
    }

    @Override
    public double calcularDescuento() {
        return getCuotaBase() * 0.10;
    }

    @Override
    public String getTipo() {
        return "VIP";
    }

    @Override
    public String getExtra() {
        return super.getExtra() + ";" + accesoTaquilla;
    }
}