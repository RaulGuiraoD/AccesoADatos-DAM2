package com.cuadernoficheros.Ejercicio2;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(sc);



        } while (opcion != 6);

        sc.close();

    }

    private static void mostrarMenu(){
        System.out.println("===== GESTIÓN DE NOTAS =====");
        System.out.println("1. Salir");
        System.out.println("2. Escribir observaciones/notas");
        System.out.println("3. Leer todas las notas");
        System.out.println("4. Consultar estadísticas de caracteres");
        System.out.println("5. Buscar una palabra clave");
        System.out.println("6. Generar copia codificada en MAYÚSCULAS");
        System.out.println("7. Generar copia codificada en minúsculas");
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
