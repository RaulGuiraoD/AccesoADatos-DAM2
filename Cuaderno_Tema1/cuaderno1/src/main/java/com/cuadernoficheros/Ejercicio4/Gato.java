public class Gato extends Mascota {

    public Gato(String nombre, int edad, String raza) {
        super(nombre, edad, raza);
    }

    @Override
    public void emitirSonido() {
        System.out.println(getNombre() + " (Gato) dice: ¡Miau!");
    }

    @Override
    public void hacerTruco() {
        System.out.println(getNombre() + " (Gato) salta a lo alto de la estantería.");
    }
}