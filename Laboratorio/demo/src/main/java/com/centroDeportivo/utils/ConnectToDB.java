package com.centroDeportivo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectToDB {

    private static final ConnectToDB INSTANCE = new ConnectToDB();
    private Connection connection;

    private static final String URL =
            "jdbc:mysql://localhost:3306/centro_deportivo";
    private static final String USER = "root";
    private static final String PASS = "root";

    private ConnectToDB() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado a centro_deportivo");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    public static ConnectToDB getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error cerrando conexión: " + e.getMessage());
        }
    }
}