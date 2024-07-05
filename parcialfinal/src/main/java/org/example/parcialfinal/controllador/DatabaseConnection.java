package org.example.parcialfinal.controllador;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static volatile Connection con = null;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://127.0.0.1:3306/pooparcialfinal";
                String user = "parcial";
                String pass = "1234";
                con = DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;  // Reset the connection to null
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }
}
