package com.cuadernoficheros.Ejercicio1;
import java.io.*;
import java.util.Scanner;

public class OperacionesTaller {
    
    public static void crearFichero(String nombre){
       File fichero = new File(nombre);     // Creamos fichero

       try {                                //Envolvemos en un try catcg
        if (fichero .exists()){             //Si existe, mensaje de que existe
            System.out.println("El fichero ya existe");
        }else {                             // Si no, lo crea y mensaje de creado
            fichero.createNewFile();
            System.out.println("El fichero ha sido creado correctamente");
        }
       } catch (IOException e) {
          System.out.println("Error al craer fichero" + e.getMessage());
       }
    }

    public static void leerFichero(String nombre){  
        File fichero = new File(nombre);     // Creamos fichero

        if(!fichero.exists()){                 //Si el fichero no existe, le devolvemos
            System.out.println("El fichero no existe");
            return;
        }

        if(fichero.length() == 0){
            System.out.println("El fichero está vacio, no hay nada que leer");
            return;
        }
        //Si existe, envolvemos todo en un try catch
        try (BufferedReader br = new BufferedReader(new FileReader(fichero)) ){   // Creamos BufferedRead
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void escribirFichero(String nombre, Scanner sc){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre, true))){
            
            System.out.println("Nombre de la pieza:");
            String piezaNombre = sc.nextLine();
            System.out.println("Categoria:  ");
            String categoria = sc.nextLine();
            System.out.println("Código Identificador:");
            String codigoIdentificador = sc.nextLine();
            System.out.println("Precio en €: ");
            String precio = sc.nextLine();

            String linea = categoria + ":" + codigoIdentificador + ":" + piezaNombre + ":" + precio;

            bw.write(linea);
            bw.newLine();

            System.out.println("Datos guardados correctamente");

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public static void mostrarGamaAlta(String nombre) {

        File fichero = new File(nombre);
 
        if (!fichero.exists()) {
            System.out.println("El fichero no existe");
            return;
        }

        boolean hayPiezas = false;
 
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
 
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
 
                if (datos.length != 4) {
                    continue; 
                }
 
                try {
                    double precio = Double.parseDouble(datos[3]);
 
                    if (precio >= 50.00) {
                        System.out.println(linea);
                        hayPiezas = true;
                    }
                } catch (NumberFormatException e) {
                    continue; 
                }
            }
 
            if (!hayPiezas) {
                System.out.println("No hay piezas que superen dicho precio");
            }
 
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

}
