package com.cuaderno2;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String URL = "jdbc:sqlite:act01.db";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;
        do {
            System.out.println("\n--- DEPARTAMENTOS ---");
            System.out.println("1. Insertar");
            System.out.println("2. Listar");
            System.out.println("3. Modificar");
            System.out.println("4. Borrar");
            System.out.println("0. Salir");
            System.out.print("Elige: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: insertar(); break;
                case 2: listar();   break;
                case 3: modificar(); break;
                case 4: borrar();   break;
                case 0: System.out.println("Adiós"); break;
                default: System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    // CREATE
    static void insertar() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();

        String sql = "INSERT INTO departamento (nombre, ubicacion) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, ubicacion);
            ps.executeUpdate();
            System.out.println("Departamento insertado");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // READ
    static void listar() {
        String sql = "SELECT * FROM departamento";
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | "
                        + rs.getString("nombre") + " | "
                        + rs.getString("ubicacion"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // UPDATE
    static void modificar() {
        System.out.print("Id del departamento a modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nueva ubicación: ");
        String ubicacion = sc.nextLine();

        String sql = "UPDATE departamento SET nombre = ?, ubicacion = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, ubicacion);
            ps.setInt(3, id);
            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Modificado" : "No existe ese id");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // DELETE
    static void borrar() {
        System.out.print("Id del departamento a borrar: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM departamento WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            System.out.println(filas > 0 ? "Borrado" : "No existe ese id");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}