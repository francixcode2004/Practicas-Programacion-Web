package com.productos.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {
    private Statement St;
    private String driver = "org.postgresql.Driver";
    private String user = "postgres";
    private String pwd = "juan1234";
    private String cadena = "jdbc:postgresql://localhost:5432/postgres";
    private Connection con;

    public Connection getConexion() { 
        return this.con; 
    }

    public Conexion() {
        this.con = crearConexion();
    }

    private Connection crearConexion() {
        try {
            // Load the PostgreSQL driver
            Class.forName(driver);
            // Connect to the database
            Connection con = DriverManager.getConnection(cadena, user, pwd);
            System.out.println("Se conecto a la base de datos postgres");
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error creating connection: " + e.getMessage());
            return null;
        }
    }

    public String Ejecutar(String sql) {
        String error = "";
        try {
            St = getConexion().createStatement();
            St.execute(sql);
            error = "Datos insertados";
        } catch (Exception ex) {
            error = "Error al ejecutar SQL: " + ex.getMessage();
        }
        return error;
    }

    public ResultSet Consulta(String sql) {
        ResultSet reg = null;
        try {
            St = getConexion().createStatement();
            reg = St.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Error en consulta SQL: " + e.getMessage());
        }
        return reg;
    }
}
