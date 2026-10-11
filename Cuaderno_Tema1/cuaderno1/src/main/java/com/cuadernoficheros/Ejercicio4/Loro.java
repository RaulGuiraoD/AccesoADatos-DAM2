public class Loro extends Mascota {

    public Loro(String nombre, int edad, String raza) {
        super(nombre, edad, raza);
    }

    @Override
    public void emitirSonido() {
        System.out.println(getNombre() + " (Loro) dice: ¡Hola, hola!");
    }

    @Override
    public void hacerTruco() {
        System.out.println(getNombre() + " (Loro) repite una frase que ha oído.");
    }
}