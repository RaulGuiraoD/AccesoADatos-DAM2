public class PaqueteTuristico {

    private String destino;
    private int duracionDias;
    private double precio;
    private String[] incluye;

    public PaqueteTuristico(String destino, int duracionDias, double precio, String[] incluye) {
        this.destino = destino;
        this.duracionDias = duracionDias;
        this.precio = precio;
        this.incluye = incluye;
    }

    public String getDestino() {
        return destino;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public double getPrecio() {
        return precio;
    }

    public String[] getIncluye() {
        return incluye;
    }
}