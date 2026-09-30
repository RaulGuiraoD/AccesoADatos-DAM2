// ============================================================
// SIMULACROEXAMENMAIN.JAVA
// ============================================================

package com.SimulacroExamen;

import com.SimulacroExamen.Libro;
import com.SimulacroExamen.LibroFisico;
import com.SimulacroExamen.LibroDigital;
import com.SimulacroExamen.GestorFicheros;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SimulacroExamenMain {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        ArrayList<Libro> biblioteca = new ArrayList<>();

        boolean programa = true;

        // ====================================================
        // CARGAR LOS LIBROS
        // ====================================================

        File fichero = new File("libros.dat");

        if (fichero.exists()) {
            biblioteca = GestorFicheros.deserializar("libros.dat");
        } else {
            biblioteca = GestorFicheros.leerCSV("libros.csv");
        }

        // ====================================================
        // MENÚ
        // ====================================================

        while (programa) {

            System.out.println("\n===== BIBLIOTECA =====");
            System.out.println("1- Registrar libro");
            System.out.println("2- Mostrar libros por categoría");
            System.out.println("3- Mostrar todos los libros");
            System.out.println("4- Guardar objetos mediante serialización");
            System.out.println("5- Recuperar objetos serializados");
            System.out.println("6- Buscar libro por ID");
            System.out.println("7- Eliminar libro");
            System.out.println("8- Mostrar precio medio");
            System.out.println("9- Salir");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:

                    System.out.println("Indica el tipo de libro");
                    System.out.println("1- Físico");
                    System.out.println("2- Digital");

                    int tipo = teclado.nextInt();
                    teclado.nextLine();

                    int id = obtenerSiguienteId(biblioteca);
                    String titulo;
                    String autor;
                    String categoria;
                    double precio;

                    if (tipo == 1) {

                        System.out.println("Indica el título");
                        titulo = teclado.nextLine();

                        System.out.println("Indica el autor");
                        autor = teclado.nextLine();

                        System.out.println("Indica la categoría");
                        categoria = teclado.nextLine();

                        System.out.println("Indica el precio");
                        precio = teclado.nextDouble();

                        System.out.println("Indica el número de páginas");
                        int numeroPaginas = teclado.nextInt();
                        teclado.nextLine();

                        LibroFisico Libro = new LibroFisico(id, titulo, autor, categoria, precio, numeroPaginas);

                        biblioteca.add(Libro);

                        GestorFicheros.guardarCSV("libro.csv", Libro);
                        
                        System.out.println("Libro registrado correctamente.");

                    } else if (tipo == 2) {

                        System.out.println("Indica el título");
                        titulo = teclado.nextLine();

                        System.out.println("Indica el autor");
                        autor = teclado.nextLine();

                        System.out.println("Indica la categoría");
                        categoria = teclado.nextLine();

                        System.out.println("Indica el precio");
                        precio = teclado.nextDouble();

                        System.out.println("Indica el tamaño en MB");
                        double tamanioMB = teclado.nextDouble();
                        teclado.nextLine();

                        LibroDigital Libro = new LibroDigital(id, titulo, autor, categoria, precio, tamanioMB);

                        biblioteca.add(Libro);

                        GestorFicheros.guardarCSV("libro.csv", Libro);
                        System.out.println("Libro regsitrado correctamente.");
                        
                    } else {
                        System.err.println("Tipo de libro incorrecto");
                    }
                    break;
                case 2:
                    System.out.println("Indica la categoría");
                    String categoriaBuscar = teclado.nextLine();
                    mostrarPorCategoria(biblioteca, categoriaBuscar);
                    break;
                case 3:
                    if (biblioteca.isEmpty()) {
                        System.out.println("No hay libros almacenados");
                    } else {
                        for (Libro libro : biblioteca) {
                            System.out.println(libro);
                        }
                    }
                    break;
                case 4:
                    GestorFicheros.serializar("libros.dat", biblioteca);
                    break;
                case 5:
                    ArrayList<Libro> librosRecuperados = GestorFicheros.deserializar("libros.dat");
                    if (librosRecuperados != null) {
                        biblioteca = librosRecuperados;
                    }
                    break;
                case 6:
                    System.out.println("Indica el ID del libro");
                    int idBuscar = teclado.nextInt();
                    teclado.nextLine();
                    Libro libroEncontrado = buscarPorId(biblioteca, idBuscar);
                    if (libroEncontrado != null) {
                        System.out.println("Libro encontrado;");
                        System.out.print(libroEncontrado);
                    } else {
                        System.out.println("No existe libro con ese ID.");
                    }
                    
                    break;
                case 7:
                    System.out.println("Indica el ID del libro que quieres eliminar");
                    int idEliminar = teclado.nextInt();
                    teclado.nextLine();
                    eliminarLibro(biblioteca, idEliminar);
                    break;
                case 8:

                    double precioMedio = calcularPrecioMedio(biblioteca);
                    if (biblioteca.isEmpty()) {
                        System.out.println("No hay libros almacenados");
                    } else {
                        System.out.println("El precio medio es: " + precioMedio);
                    }
                    break;
                case 9:
                    programa = false;
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opción no permitida");
            }
        }
        teclado.close();
    }

    // ========================================================
    // MÉTODOS
    // ========================================================

    public static int obtenerSiguienteId(ArrayList<Libro> biblioteca) {
        int mayor = 0;
        for (Libro libro : biblioteca) {
            if (libro.getId() > mayor) {
                mayor = libro.getId();
            }
        }
        return mayor + 1;
    }

    public static Libro buscarPorId(ArrayList<Libro> biblioteca, int id) {
        for (Libro libro : biblioteca) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    public static void mostrarPorCategoria(ArrayList<Libro> biblioteca, String categoria) {
        Boolean encontrado = false;
        for (Libro libro : biblioteca) {
            if (libro.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(libro);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No existen libros de esa categoria");
        }
    }

    public static void eliminarLibro(ArrayList<Libro> biblioteca, int id) {
        Libro libro = buscarPorId(biblioteca, id);
        if (libro !=null) {
            biblioteca.remove(libro);
            System.out.println("Libro borrado correctamente");
        } else {
            System.out.println("No existen libros con ese ID");
        }
    }

    public static double calcularPrecioMedio(ArrayList<Libro> biblioteca) {
        if (biblioteca.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Libro libro : biblioteca) {
            suma += libro.getPrecio();
        }
        return suma / biblioteca.size();
    }
}
