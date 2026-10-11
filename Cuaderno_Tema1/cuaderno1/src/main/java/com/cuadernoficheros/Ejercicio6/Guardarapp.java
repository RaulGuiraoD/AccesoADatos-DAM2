import java.util.ArrayList;
import java.util.List;

public class GuardarApp {

    public static void main(String[] args) {
        List<Comic> comics = new ArrayList<>();
        comics.add(new Comic("Amazing Fantasy #15", "Marvel", 12500.00, "CLAVE-A1"));
        comics.add(new Comic("Detective Comics #27", "DC", 9800.50, "CLAVE-B2"));
        comics.add(new Comic("La Última Cacería de Kraven", "Marvel", 15000.00, "CLAVE-C3"));
        comics.add(new Comic("Lobezno: El Viejo Logan", "Marvel", 350.75, "CLAVE-D4"));

        OperacionesComic.guardarColeccion(comics, "coleccion.dat");
    }
}