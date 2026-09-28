package com.cuadernoficheros.Ejercicio1;
import java.util.Scanner;

public class TallerApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del fichero a crear: ");
                    OperacionesTaller.crearFichero(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Nombre del fichero a leer: ");
                    OperacionesTaller.leerFichero(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Nombre del fichero donde escribir: ");
                    String nombre = sc.nextLine();
                    OperacionesTaller.escribirFichero(nombre, sc);
                }
                case 4 -> {
                    System.out.print("Nombre del fichero a comprobar: ");
                    OperacionesTaller.mostrarGamaAlta(sc.nextLine());
                }
                case 5 -> System.out.println("Saliendo de la aplicación...");
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
            System.out.println();

        } while (opcion != 5);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== TALLER MECÁNICO =====");
        System.out.println("1. Crear fichero");
        System.out.println("2. Leer fichero");
        System.out.println("3. Escribir en fichero");
        System.out.println("4. Mostrar piezas de gama alta");
        System.out.println("5. Salir");
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