import dom.GestorInventarioDOM;
import java.util.Scanner;
import sax.LectorInventarioSAX;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorInventarioDOM gestorDOM = new GestorInventarioDOM();
        LectorInventarioSAX lectorSAX = new LectorInventarioSAX();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            if (opcion < 1 || opcion > 6) {
                System.out.println("La opción de menú debe estar comprendida entre 0 y 5.");
                System.out.println();
                continue;
            }

            switch (opcion) {
                case 1:
                    gestorDOM.anadirProducto(sc);
                    break;
                case 2:
                    gestorDOM.mostrarProductos();
                    break;
                case 3:
                    lectorSAX.buscarPorCategoria(sc);
                    break;
                case 4:
                    lectorSAX.mostrarPocoStock(sc);
                    break;
                case 5:
                    gestorDOM.actualizarStock(sc);
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicación...");
                    break;
            }

            System.out.println();
        } while (opcion != 6);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("========== GESTIÓN DE INVENTARIO ==========");
        System.out.println("1. Añadir producto");
        System.out.println("2. Mostrar todos los productos (DOM)");
        System.out.println("3. Buscar productos por categoría (SAX)");
        System.out.println("4. Mostrar productos con poco stock (SAX)");
        System.out.println("5. Actualizar stock (DOM)");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}