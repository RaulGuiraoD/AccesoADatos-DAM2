package com.cuadernoficheros.Ejercicio3;

import java.util.Scanner;

public class GestionesEstudiantes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                    OperacionesEstudiantes.insertarEstudiante(sc);
                    break;
                case 2:
                    OperacionesEstudiantes.consultarTodos();
                    break;
                case 3:
                    OperacionesEstudiantes.consultarPorMatricula(sc);
                    break;
                case 4:
                    OperacionesEstudiantes.actualizarEstudiante(sc);
                    break;
                case 5:
                    OperacionesEstudiantes.eliminarEstudiante(sc);
                default:
                    System.out.println("Opción no valida, intenta de nuevo");
            }
            System.out.println();
        } while (opcion != -1);

        sc.close();
    }

    private static void mostrarMenu (){
        System.out.println("GESTIÓN DE ESTUDIANTES");
        System.out.println("===== GESTIÓN DE ESTUDIANTES =====");
        System.out.println("0. Salir del programa");
        System.out.println("1. Insertar estudiante");
        System.out.println("2. Consultar todos los estudiantes");
        System.out.println("3. Consultar un estudiante por matrícula");
        System.out.println("4. Actualizar estudiante por matrícula");
        System.out.println("5. Eliminar estudiante por matrícula");
        System.out.print("Elige una opción: ");
    }

    private static int leerOpcion(Scanner sc){
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return  -1;
        }
    }
}
