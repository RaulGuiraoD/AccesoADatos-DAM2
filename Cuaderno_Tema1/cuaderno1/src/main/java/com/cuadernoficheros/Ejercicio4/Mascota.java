public abstract class Mascota {

    private String nombre;
    private int edad;
    private String raza;

    public Mascota(String nombre, int edad, String raza) {
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getRaza() {
        return raza;
    }

    public abstract void emitirSonido();

    public abstract void hacerTruco();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [nombre=" + nombre + ", edad=" + edad + ", raza=" + raza + "]";
    }
}