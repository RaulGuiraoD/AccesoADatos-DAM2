package modelos;

public abstract class Socio {

    private String id;
    private String nombre;
    private Plan plan;
    private double cuotaBase;

    public Socio(String id, String nombre, Plan plan, double cuotaBase) {
        if (cuotaBase <= 0) {
            throw new IllegalArgumentException("La cuota base debe ser mayor que 0");
        }
        this.id = id;
        this.nombre = nombre;
        this.plan = plan;
        this.cuotaBase = cuotaBase;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Plan getPlan() {
        return plan;
    }

    public double getCuotaBase() {
        return cuotaBase;
    }

    public abstract double calcularCuota();

    public abstract String getTipo();

    public abstract String getExtra();
}