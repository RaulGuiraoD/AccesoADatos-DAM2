import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperacionesComic {

    // Guarda primero el nº de cómics (un int) y después cada cómic
    public static void guardarColeccion(List<Comic> comics, String ruta) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {

            oos.writeInt(comics.size()); 

            for (Comic c : comics) {     
                oos.writeObject(c);
            }

            System.out.println("Se han guardado " + comics.size() + " cómics en " + ruta);

        } catch (IOException e) {
            System.out.println("Error al guardar la colección: " + e.getMessage());
        }
    }

    public static List<Comic> leerColeccion(String ruta) {
        List<Comic> comics = new ArrayList<>();
        File fichero = new File(ruta);

        if (!fichero.exists()) {
            System.out.println("El fichero " + ruta + " no existe");
            return comics;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {

            int total = ois.readInt(); 

            for (int i = 0; i < total; i++) {
                comics.add((Comic) ois.readObject());
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer la colección: " + e.getMessage());
        }

        return comics;
    }
}