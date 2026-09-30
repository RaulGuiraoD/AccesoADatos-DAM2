// ============================================================
// GESTORFICHEROS.JAVA
// ============================================================

package com.SimulacroExamen;


import com.SimulacroExamen.Libro;
import com.SimulacroExamen.LibroFisico;
import com.SimulacroExamen.LibroDigital;

import java.io.*;
import java.util.ArrayList;

public class GestorFicheros {

    public static ArrayList<Libro> leerCSV(String nombreFichero) {

        ArrayList<Libro> biblioteca = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))){
            String linea;
            Boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String [] datos = linea.split(";");
                String tipo = datos[0];

                int id = Integer.parseInt(datos[1]);
                String titulo = datos[2];
                String autor = datos[3];
                String categoria = datos [4];
                double precio = Double.parseDouble(datos[5]);

                if (tipo.equalsIgnoreCase("FISICO")) {
                    int numeroPaginas = Integer.parseInt(datos[6]);
                    LibroFisico libro = new LibroFisico(id, titulo, autor, categoria, precio, numeroPaginas);
                    biblioteca.add(libro);
                } else if (tipo.equalsIgnoreCase("DIGITAL")) {
                    double tamanioMB = Double.parseDouble(datos[6]);
                    LibroDigital libro = new LibroDigital(id, titulo, autor, categoria, precio, tamanioMB);
                    biblioteca.add(libro);
                }
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el fichero CSV" + e.getMessage());
        }

        return biblioteca;
    }

    public static void guardarCSV(String nombreFichero, Libro libro) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero, true))){
            if (libro instanceof LibroFisico) {
                LibroFisico libroFisico = (LibroFisico) libro;
                bw.write("FISICO;" + libroFisico.getId() + ";" + libroFisico.getAutor() + ";" + libroFisico.getCategoria() + ";" + libroFisico.getPrecio() + ";" + libroFisico.getNumeroPaginas() + ";");
            } else if (libro instanceof LibroDigital) {
                LibroDigital libroDigital = (LibroDigital) libro;
                bw.write("DIGITAL;" + libroDigital.getId() + ";" + libroDigital.getAutor() + ";" + libroDigital.getCategoria() + ";" + libroDigital.getPrecio() + ";" + libroDigital.getTamanioMB() + ";");
            }
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar en CSV:" + e.getMessage());
        }
    }

    public static void serializar(String nombreFichero,ArrayList<Libro> biblioteca) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))){
            oos.writeObject(biblioteca);;
            System.out.println("Biblioteca serializada correctamente.");
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al serializar: " + e.getMessage());    }

    }

    public static ArrayList<Libro> deserializar(String nombreFichero) {
        ArrayList <Libro> biblioteca = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))){
            biblioteca = (ArrayList<Libro>) ois.readObject();
            ois.close();
            System.out.println("Biblioteca recuperada correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar: " + e.getMessage());
        }

        return biblioteca;
    }
}
