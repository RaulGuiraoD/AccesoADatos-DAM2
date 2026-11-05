package com.conectores;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ejecutarScriptMySQL();
    }


    public static void pruebaPreparada() {
        System.out.println("Error por pantalla");


        String URI = "jdbc:mysql://localhost:3306/ejemplo";
        String sql = "INSERT INTO departamentos VALUES (?,?,?)";
        try {
            Connection conexion = DriverManager.getConnection(URI, "root", "root");


            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setString(2, "JUAN");
            ps.setString(3, "ZGZ");
            int filas = ps.executeUpdate();


        } catch (SQLException e) {
            // TODO: handle exception
        }
    }


    public static void pruebaExecute() {
        String URI = "jdbc:mysql://localhost:3306/ejemplo";
        String sql = "SELECT * FROM departamentos";
        try {
            Connection conexion = DriverManager.getConnection(URI, "root", "root");
            Statement statement = conexion.createStatement();


            boolean valor = statement.execute(sql);
            // System.out.println("Contiene elementos: "+ valor);


            if (valor) {
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getString(3));
                }
                rs.close();
            }


        } catch (SQLException e) {
            // TODO: handle exception
        }
    }


    public static void consultarBD() {
        String URI = "jdbc:mysql://localhost:3306/ejemplo";
        try {
            Connection conexion = DriverManager.getConnection(URI, "root", "root");
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet resul = null;


            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();


            System.out.printf("La base de datos %s utiliza el driver %s \n", nombre, driver);


            resul = dbmd.getTables("ejemplo", null, null, null);
            while (resul.next()) {
                System.out.println("Tabla: " + resul.getString(3));


            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private static void ejecutarScriptMySQL() {
        File scriptFile = new File("C:\\Repos\\script.sql");
        System.out.println("Fichero de consulta: " + scriptFile.getName());
        String consulta = "";
        System.out.println("Convirtiendo fichero a cadena...");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(scriptFile));
            String linea = null;
            StringBuilder stringBuilder = new StringBuilder();
            String salto = System.getProperty("line.separator");


            try {
                while ((linea = br.readLine()) != null) {
                    stringBuilder.append(linea);
                    // stringBuilder.append(salto);
                }
            } catch (IOException e) {
                System.out.println("Error E/S");
            }
            consulta = stringBuilder.toString();
            System.out.println(consulta);


        } catch (Exception e) {
            // TODO: handle exception
        }


        try {
            String URI = "jdbc:mysql://localhost:3306/ejemplo?allowMultiQueries=true";
            Connection connection = DriverManager.getConnection(URI, "root", "root");
            Statement sents = connection.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Datos cargados correctamente");
            connection.close();
            sents.close();
        } catch (SQLException e) {
            System.out.println("error:" + e.getMessage());
            // TODO: handle exception
        }


    }


}








