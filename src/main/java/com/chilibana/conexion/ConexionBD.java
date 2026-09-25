package com.chilibana.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/registro_pedidos_chilibana";

    private static final String USUARIO = "root";

    private static final String CONTRASENA =
            System.getenv("CHILIBANA_DB_PASSWORD");

    public static Connection conectar() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "No se encontró el controlador de MySQL.", e);
        }

        return DriverManager.getConnection(
                URL, USUARIO, CONTRASENA);
    }

    public static void main(String[] args) {

        try {
            Connection conexion = conectar();

            System.out.println(
                    "Conexión exitosa a la base de datos.");

            conexion.close();

        } catch (SQLException e) {
            System.out.println(
                    "Error de conexión: " + e.getMessage());
        }
    }
}