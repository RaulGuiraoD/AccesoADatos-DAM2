public class RefugioApp {

    public static void main(String[] args) {
        Refugio refugio = new Refugio();

        CuidadorMascota ana = new CuidadorMascota("Ana");
        CuidadorMascota luis = new CuidadorMascota("Luis");
        refugio.agregarCuidador(ana);
        refugio.agregarCuidador(luis);
        refugio.agregarMascota(new Perro("Rex", 3, "Labrador"));
        refugio.agregarMascota(new Perro("Luna", 5, "Beagle"));
        refugio.agregarMascota(new Perro("Toby", 2, "Bulldog"));
        refugio.agregarMascota(new Gato("Misi", 4, "Siamés"));
        refugio.agregarMascota(new Gato("Garfield", 6, "Persa"));
        refugio.agregarMascota(new Gato("Nala", 1, "Común europeo"));
        refugio.agregarMascota(new Loro("Kiko", 8, "Guacamayo"));
        refugio.agregarMascota(new Loro("Rio", 3, "Cotorra"));
        refugio.agregarMascota(new Huron("Rayo", 2, "Albino"));

        System.out.println("--- Sonidos y trucos (polimorfismo) ---");
        for (Mascota m : refugio.getMascotas()) {
            m.emitirSonido();
            m.hacerTruco();
        }

        System.out.println("\n--- Asignación automática ---");
        refugio.asignarMascotasAutomaticamente();

        System.out.println("\n--- Probar el límite de un cuidador ---");
        ana.asignarMascota(new Gato("Extra", 1, "Común europeo"));

        System.out.println("\n--- Entrenar todas ---");
        refugio.entrenarTodas();

        System.out.println("\n--- Estado del refugio ---");
        refugio.mostrarMascotasPorCuidador();
    }
}