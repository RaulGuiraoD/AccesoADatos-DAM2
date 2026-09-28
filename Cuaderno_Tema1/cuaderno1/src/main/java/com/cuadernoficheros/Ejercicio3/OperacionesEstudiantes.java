package com.cuadernoficheros.Ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperacionesEstudiantes {
    
    private static final String FICHERO = "estudiantes.txt";

    private static  List<Estudiante> leerEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                try {
                    lista.add(new  Estudiante(linea));
                } catch (Exception e) {
                   System.out.println("Formateo erroneo, linea ignroada " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

}
