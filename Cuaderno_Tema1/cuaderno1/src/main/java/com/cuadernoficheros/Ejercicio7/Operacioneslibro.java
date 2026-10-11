import java.io.File;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class OperacionesLibro {

    private static final String FICHERO = "mi_libro.xml";

    public static void generarXML(Scanner sc) {
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        Libro libro = new Libro(isbn, titulo, autor);

        try {
            JAXBContext context = JAXBContext.newInstance(Libro.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(libro, new File(FICHERO));

            System.out.println("¡Archivo XML generado con éxito!");

        } catch (JAXBException e) {
            System.out.println("Error al generar el XML: " + e.getMessage());
        }
    }

    public static void leerXML() {
        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            System.out.println("El fichero " + FICHERO + " no existe. Genera uno primero (opción 1).");
            return;
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Libro.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Libro libro = (Libro) unmarshaller.unmarshal(fichero);

            System.out.println("--- DATOS DEL LIBRO CARGADO ---");
            System.out.println("ISBN: " + libro.getIsbn());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());

        } catch (JAXBException e) {
            System.out.println("Error al leer el XML: " + e.getMessage());
        }
    }
}