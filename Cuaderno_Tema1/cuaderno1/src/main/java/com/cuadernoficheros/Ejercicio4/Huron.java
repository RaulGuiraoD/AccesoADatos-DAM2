public class Huron extends Mascota {

    public Huron(String nombre, int edad, String raza) {
        super(nombre, edad, raza);
    }

    @Override
    public void emitirSonido() {
        System.out.println(getNombre() + " (Hurón) dice: ¡Dook dook!");
    }

    @Override
    public void hacerTruco() {
        System.out.println(getNombre() + " (Hurón) se cuela por un tubo.");
    }
}