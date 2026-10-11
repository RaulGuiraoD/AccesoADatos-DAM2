public class Perro extends Mascota {

    public Perro(String nombre, int edad, String raza) {
        super(nombre, edad, raza);
    }

    @Override
    public void emitirSonido() {
        System.out.println(getNombre() + " (Perro) dice: ¡Guau guau!");
    }

    @Override
    public void hacerTruco() {
        System.out.println(getNombre() + " (Perro) da la patita.");
    }
}