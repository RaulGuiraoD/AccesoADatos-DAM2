package com.cuadernoficheros.Ejercicio2;
import java.util.Scanner;

public class NotasApp {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            if (opcion < 0 || opcion > 6) {
                System.out.println("La opción de menú debe estar comprendida entre 0 y 6.");
                System.out.println();
                continue;
            }

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                case 1:
                    GestionNotas.escribirNotas(sc);
                    break;
                case 2:
                    GestionNotas.leerNotas();
                    break;
                case 3:
                    GestionNotas.estadisticas();
                    break;
                case 4:
                    GestionNotas.buscarPalabra(sc);
                    break;
                case 5:
                    GestionNotas.generarMayusculas();
                    break;
                case 6:
                    GestionNotas.generarMinusculas();
                    break;
            }

            System.out.println();
        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== GESTIÓN DE NOTAS =====");
        System.out.println("0. Salir del programa");
        System.out.println("1. Escribir observaciones/notas");
        System.out.println("2. Leer todas las notas");
        System.out.println("3. Consultar estadísticas de caracteres");
        System.out.println("4. Buscar una palabra clave");
        System.out.println("5. Generar copia en MAYÚSCULAS");
        System.out.println("6. Generar copia en minúsculas");
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
