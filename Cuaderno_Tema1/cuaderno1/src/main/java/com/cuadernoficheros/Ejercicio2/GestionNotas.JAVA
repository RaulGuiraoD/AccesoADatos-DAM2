package com.cuadernoficheros.Ejercicio2;
import java.io.*;
import java.util.Scanner;

public class GestionNotas {

    private static final String FICHERO = "notas.txt";

    public static void escribirNotas(Scanner sc) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO, true))) {
            System.out.println("Escribe tus notas ('FIN' para terminar)");
            String linea = sc.nextLine();
            
            while (!linea.equals("FIN")) {
                bw.write(linea);
                bw.newLine();
                linea = sc.nextLine();    
            }
            System.out.println("Notas guardadas correctamente");

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero" + e.getMessage());
        }

    }

    public static void  leerNotas () {
        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            System.out.println("El fichero no existe, intentelo de nuevo");
            return ;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);

            }
        } catch (Exception e) {
            System.out.println("Error al leer en el fichero" + e.getMessage());
        }
    }

    public static void estadisticas () {
        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            System.out.println("El fichero no existe, intentelo de nuevo.");
        }

        int totalCaracteres = 0;
        int totalLineas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
            String linea;
            while ((linea = br.readLine()) !=null) {
                totalCaracteres += linea.length();
                totalLineas++;
            }

            System.out.println("Total caracteres: " + totalCaracteres);
            System.out.println("Total líneas: " + totalLineas);
        } catch (Exception e) {
            System.out.println("Error al leer en el fichero" + e.getMessage());
        }
    }

    public static void buscarPalabra(Scanner sc) {
        String palabra;

        do {
            System.out.print("Introduce la palabra que quieres buscar (sin espacios):");
            palabra = sc.nextLine();

            if (palabra.contains((" "))) {
                System.out.println("Error: la palabra contiene espacios.");
                return;
            }
        } while (palabra.contains(" "));

        File fichero = new File(FICHERO);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe todavía");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
            String linea;
            int numeroLinea = 0;
            boolean palabraEncontrada = false;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                if (linea.contains(palabra)){
                    System.out.println("La palabra se encuentra en la línea " + numeroLinea + " del fichero");
                    palabraEncontrada = true;
                    break;
                }
            }

            if (!palabraEncontrada) {
                System.out.println("La palabra no ha sido encontrada en el fichero.");
                
            }
        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void generarMayusculas(){
        File fichero = new File(FICHERO);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe todavía");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero));
            BufferedWriter bw = new BufferedWriter(new FileWriter("notas_mayusculas.txt"))){
                String linea;

                while ((linea = br.readLine()) != null) {
                    bw.write(linea.toUpperCase());
                    bw.newLine();
                }
            
            System.out.println("Copia generada en notas_mayusculas.txt");

        } catch (Exception e) {
            System.out.println("Error al generar la copia: " + e.getMessage());
        }
    }

    public static void generarMinusculas(){
        File fichero = new File(FICHERO);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe todavía");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero));
            BufferedWriter bw = new BufferedWriter(new FileWriter("notas_minusculas.txt"))){
                String linea;

                while ((linea = br.readLine()) != null) {
                    bw.write(linea.toLowerCase());
                    bw.newLine();
                }
            
            System.out.println("Copia generada en notas_minusculas.txt");

        } catch (Exception e) {
            System.out.println("Error al generar la copia: " + e.getMessage());
        }
    }

}