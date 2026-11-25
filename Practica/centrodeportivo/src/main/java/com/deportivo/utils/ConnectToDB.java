package com.deportivo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectToDB {

    private static final ConnectToDB INSTANCE = new ConnectToDB();
    private Connection connection;

    //Lineas dependiendo de la bbdd
    private static final String URL = "jdbc:sqlite:instituto.db"; // DERBY: jdbc:derby:institutoDerby;create=true  || H2: jdbc:h2./institutoH2  ||  HSQLDB jdbc:hsqldb:file:./institutoHSQL
    private static final String USER = "";  // H2: "sa"   HSQLDB: "SA"
    private static final String PASS = "";
    
    private ConnectToDB() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.err.println("Error de conexion" + e.getMessage());
        }
    }

    public static ConnectToDB getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection(){
        try {
            if(connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            System.err.println("Error cerraando conexión" + e.getMessage());
        }
    }   

}
