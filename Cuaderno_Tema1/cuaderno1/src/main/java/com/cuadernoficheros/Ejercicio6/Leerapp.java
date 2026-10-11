import java.util.List;

public class LeerApp {

    public static void main(String[] args) {
        List<Comic> comics = OperacionesComic.leerColeccion("coleccion.dat");

        if (comics.isEmpty()) {
            System.out.println("No hay cómics que mostrar.");
            return;
        }

        System.out.println("Cómics en la colección: " + comics.size());
        for (Comic c : comics) {
            System.out.println(c);
        }
    }
}