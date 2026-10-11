import java.util.Scanner;

public class LibrosApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1:
                    OperacionesLibro.generarXML(sc);
                    break;
                case 2:
                    OperacionesLibro.leerXML();
                    break;
                case 3:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }

            System.out.println();
        } while (opcion != 3);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== LIBROS DIGITALES =====");
        System.out.println("1. Generar XML");
        System.out.println("2. Leer XML");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    private static int leerOpcion(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}