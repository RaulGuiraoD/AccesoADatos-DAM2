import java.util.Scanner;
import servicios.GestionSocios;

public class Main {

    private static final String RUTA_CSV = "socios.csv";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionSocios gestion = new GestionSocios();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1:
                    gestion.agregarSocio(sc);
                    break;
                case 2:
                    gestion.listarSocios();
                    break;
                case 3:
                    System.out.printf("Recaudación total mensual: %.2f €%n", gestion.calcularRecaudacionTotal());
                    break;
                case 4:
                    gestion.guardarCSV(RUTA_CSV);
                    break;
                case 5:
                    gestion.cargarCSV(RUTA_CSV);
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }

            System.out.println();
        } while (opcion != 6);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== GIMNASIO =====");
        System.out.println("1. Agregar socio");
        System.out.println("2. Listar socios");
        System.out.println("3. Calcular recaudación total");
        System.out.println("4. Guardar datos a CSV");
        System.out.println("5. Cargar datos desde CSV");
        System.out.println("6. Salir");
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