package com.cuadernoficheros.Ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        return lista;
    }

    private static void guardarEstudiante(List<Estudiante> lista){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO))){
            for (Estudiante estudiante : lista) {
                bw.write(estudiante.toStringWithSeparators());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static Estudiante buscar(List<Estudiante> lista, int matricula) {
        for (Estudiante estudiante : lista) {
            if (estudiante.getMatricula() == matricula) {
                return  estudiante;
            }
        }
        return null;
    }

    private static int leerMatricula(Scanner sc) {
        System.out.println("Matrícula:");
        try {
            int matricula = Integer.parseInt(sc.nextLine().trim());
            if (matricula < 0) {
                return  matricula;
            }
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }
        System.out.println("La matrícula debe ser un número entero positivo");
        return  -1;
    }

    private static  double leerNota(Scanner sc){
        System.out.println("Nota media:");
        try {
            double nota = Double.parseDouble(sc.nextLine().trim());
            if (nota >= 0 && nota <= 10) {
                return nota;
            }
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }
        System.out.println("La nota media debe ser entre 0 y 10");
        return -1;
    }


    public static  void insertarEstudiante(Scanner sc) {
        int matricula = leerMatricula(sc);
        if (matricula == 1 ) {
            return;
        }

        List<Estudiante> lista = leerEstudiantes();
        if (buscar(lista, matricula) != null) {
            System.out.println("Ya existe otro estudiante con esa matricula");
            return;
        }

        System.err.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Fecha de inscripción (dd/mm/aaaa): ");
        String fecha = sc.nextLine();
        System.out.print("Curso: ");
        String curso = sc.nextLine();
        double nota = leerNota(sc);
        if (nota == -1) {
            return ;
        }

        Estudiante nuevo = new Estudiante(matricula, nombre, fecha, curso, nota);

        try (BufferedWriter bw = new  BufferedWriter(new FileWriter(FICHERO, true))){
            bw.write(nuevo.toStringWithSeparators());
            bw.newLine();
            System.out.println("Nuevo estudiante creado en el fichero de texto");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero " + e.getMessage());
        }
    }

    public static void consultarTodos() {
        List<Estudiante> lista = leerEstudiantes();

        if (lista.isEmpty()) {
            System.out.println("El fichero de texto está vacio");
            return;
        }

        for (Estudiante estudiante : lista) {
            System.out.println(estudiante);
        }
        System.out.println("Total de estudiantes: " + lista.size());
    }

    public static void consultarPorMatricula (Scanner sc) {
        int matricula = leerMatricula(sc);
        if (matricula == -1) {
            return;
        }

        Estudiante estudiante = buscar(leerEstudiantes(), matricula);

        if (estudiante == null) {
            System.out.println("No existe ningun estudiante con la matricula ingresada");
        } else {
            System.out.println(estudiante);
        }
    }

    public static void actualizarEstudiante (Scanner sc) {
        int matricula = leerMatricula(sc);
        if (matricula == -1) {
            return;
        }
        List<Estudiante> lista = leerEstudiantes();
        Estudiante estudiante = buscar(lista, matricula);

        if (estudiante == null) {
            System.err.println("No existe ningun estudiante con la matricula ingresada");
            return ;
        }

        System.out.println("Nuevo curso");
        String curso = sc.nextLine();
        double nota = leerNota(sc);
        if (nota == -1) {
            return;
        }

        estudiante.setCurso(curso);
        estudiante.setNotaMedia(nota);
        guardarEstudiante(lista);

        System.out.println("Estudiante actualizado correctamente");
    }

    public static void eliminarEstudiante(Scanner sc){
        int matricula = leerMatricula(sc);
        if (matricula == -1) {
            return;
        }

        List <Estudiante> lista = leerEstudiantes();
        Estudiante estudiante = buscar(lista, matricula);

        if (estudiante == null) {
            System.out.println("No existe ningun estudiante con la matricula ingresada ");
            return;
        }
        lista.remove(estudiante);
        guardarEstudiante(lista);
        System.out.println("Estudiante eliminado correctamente");
    }

}
