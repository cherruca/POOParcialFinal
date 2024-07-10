package org.example.parcialfinal.controllador;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection { // 00191322 Declara la clase DatabaseConnection
    private static volatile Connection con = null; // 00191322 Declara una variable estatica y volatil de tipo Connection llamada con

    private DatabaseConnection() {} // 00191322 Constructor privado para evitar que se instancie esta clase

    public static Connection getConnection() { // 00191322 Metodo estatico para obtener la conexion a la base de datos
        if (con == null) { // 00191322 // Verifica si la conexion es nula
            try {
                String url = "jdbc:mysql://127.0.0.1:3306/pooparcialfinal"; // 00191322 URL de conexion a la base de datos MySQL
                String user = "parcial"; // 00191322 Usuario de la base de datos
                String pass = "1234"; // 00191322 Contrasena del usuario
                con = DriverManager.getConnection(url, user, pass); // 00191322 Establece la conexion usando DriverManager
            } catch (Exception e) { // 00191322 Captura excepciones de cualquier tipo
                e.printStackTrace(); // 00191322 Imprime el rastreo de la excepcion
                System.out.println(e); // 00191322 Imprime la excepcion en la consola
            }
        }
        return con; // 00191322 Devuelve la conexion establecida o ya existente
    }

    public static void closeConnection() { // 00191322 Metodo estatico para cerrar la conexion a la base de datos
        if (con != null) { // 00191322 Verifica si la conexion no es nula
            try { // 00191322
                con.close(); // 00191322 Cierra la conexion
                con = null;  // 00191322 Resetea la conexion a nula para liberar recursos
            } catch (Exception e) { // 00191322 Captura excepciones de cualquier tipo al cerrar la conexión
                e.printStackTrace(); // 00191322 Imprime el rastreo de la excepcion
                System.out.println(e); // 00191322 Imprime la excepción en la consola
            }
        }
    }
}
