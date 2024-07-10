package org.example.parcialfinal.controllador;

import org.example.parcialfinal.modelo.Compra;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompraControlador { // 00191322 Clase Compra controlador

    public List<Compra> obtenerCompras() {  // 00191322 metodo para obtener compras
        List<Compra> compras = new ArrayList<>(); // 00191322 se crea una lista de tipo compra
        try {
            Statement stmt = DatabaseConnection.getConnection().createStatement(); // 00191322 se crea Statement
            String query = "SELECT co.id, fecha, tarjeta_id, monto, descripcion, ta.numero " + // 00191322 se crea query
                    "FROM compra co " + // 00191322 se crea query
                    "INNER JOIN tarjeta ta ON co.tarjeta_id = ta.id;"; // 00191322 se crea query

            ResultSet rs = stmt.executeQuery(query); // 00191322 se ejecuta statement y se guarda en Resultset

            leerCompras(compras, rs); // 00191322 se lee los datos del Resultset
        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            e.printStackTrace(); // 00191322 Se imprime el Stack Trace
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return compras; // 00191322 se devuelven las compras
    }

    public List<Compra> buscarCompras(String termino) { // 00191322 metodo para buscar compras
        List<Compra> compras = new ArrayList<>(); // 00191322 se crea una lista de tipo compra
        try {
            String query = "SELECT co.id, fecha, tarjeta_id, monto, descripcion, ta.numero " +  // 00191322 se crea query
                    "FROM compra co " + // 00191322 se crea query desde compra
                    "INNER JOIN tarjeta ta ON co.tarjeta_id = ta.id " +
                    "WHERE monto LIKE ? " + // 00191322 query con monto
                    "OR descripcion LIKE ? " + // 00191322 query con descripcion
                    "OR ta.numero LIKE ? ;"; // 00191322 query con numero

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00191322 Se crea un prepared Statement y se le pasa la query

            pst.setString(1, "%" + termino + "%"); // 00191322 Se asigna parametro al prepared Statement
            pst.setString(2, "%" + termino + "%"); // 00191322 Se asigna parametro al prepared Statement
            pst.setString(3, "%" + termino + "%"); // 00191322 Se asigna parametro al prepared Statement

            ResultSet rs = pst.executeQuery(); // 00191322 se ejecuta statement y se guarda en Resultset

            leerCompras(compras, rs); // // 00191322 se lee los datos del Resultset
        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            e.printStackTrace(); // 00191322 Se imprime el Stack Trace
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return compras; // 00191322 se devuelven los compras
    }

    private void leerCompras(List<Compra> compras, ResultSet rs) throws SQLException { // 00191322 Metodo para leer los resultset
        while (rs.next()) { // 00191322 se ejecuta mientras hayan datos
            Compra compra = new Compra(); // 00191322 instancia de compra
            compra.setId(rs.getInt("id")); // 00191322 se setea el id
            compra.setFechaDeCompra(rs.getString("fecha")); // 00191322 se setea el fecha
            compra.setIdTarjeta(rs.getInt("tarjeta_id")); // 00191322 se setea el tarjeta_id
            compra.setMonto(rs.getDouble("monto")); // 00191322 se setea el monto
            compra.setDescripcion(rs.getString("descripcion")); // 00191322 se setea el descripcion
            compra.setClienteTarjeta(rs.getString("numero")); // 00191322 se setea el numero
            compras.add(compra); // 00191322 se agrega una compra a la lista
        }
    }

    public boolean persistirCompra(Compra compra) { // 00191322 metodo para guardar/actualizar la compra
        try {
            String query; // 00191322 string para la query
            int result; // 00191322 resultado que determina si salio bien o mal la transaccion
            if(Objects.isNull(compra.getId())) { // 00191322 si id es nulo, es un insert
                query = "INSERT INTO compra (fecha, tarjeta_id, monto, descripcion) VALUES (?, ?, ?, ?);"; // 00191322 query para insert
            } else {
                query = "UPDATE compra SET fecha = ?, tarjeta_id = ?, monto = ?, descripcion = ? WHERE id = ?;"; // 00191322 query para update
            }

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00191322 preparedstatement con la query
            pst.setDate(1, Date.valueOf(stringALocalDate(compra.getFechaDeCompra()))); // 00191322 se setea parametro 1 fecha
            pst.setInt(2, compra.getIdTarjeta()); // 00191322 se setea parametro 2 id tarjeta
            pst.setDouble(3, compra.getMonto()); // 00191322 se setea parametro 3 monto
            pst.setString(4, compra.getDescripcion()); // 00191322 se setea parametro 4 descripcion
            if(Objects.nonNull(compra.getId())) { // 00191322 si no es nulo, es un update
                pst.setInt(5, compra.getId()); // 00191322 se setea parametro 5 id
            }

            result = pst.executeUpdate();  // 00191322 se ejecuta el prepared statement

            if(result > 0) { // 00191322 si el resulset fue exitoso es mayor que 0
                System.out.println("EXITO"); // 00191322 se imprime en consola
                return true; // 00191322 se devuelve el exito
            } else {
                System.out.println("FRACASO"); // 00191322 se imprime en consola
                return false; // 00191322 se devulve el fracaso
            }

        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            e.printStackTrace(); // 00191322 Se imprime el Stack Trace
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return true; // 00191322 se devuelve true si todo salio bien
    }

    public boolean eliminarCompra(Integer idCompra) { // 00191322 metodo para eliminar la compra
        try {
            String query = "DELETE FROM compra WHERE id = ?"; // 00191322 string para la query delete
            int result; // 00191322 resultado que determina si salio bien o mal la transaccion
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00191322 preparedstatement con la query

            pst.setInt(1, idCompra); // 00191322 se setea parametro 1 id

            result =  pst.executeUpdate(); // 00191322 se ejecuta el prepared statement

            if(result > 0) { // 00191322 si el resulset fue exitoso es mayor que 0
                System.out.println("EXITO"); // 00191322 se imprime en consola
                return true; // 00191322 se devuelve el exito
            } else {
                System.out.println("FRACASO"); // 00191322 se imprime en consola
                return false; // 00191322 se devulve el fracaso
            }
        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            e.printStackTrace(); // 00191322 Se imprime el Stack Trace
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return true; // 00191322 si todo salio bien, se devulve true
    }

    private LocalDate stringALocalDate(String fechaString) { // 00191322 metodo para convertir fecha
        return LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 00191322 se devuelve la fecha
    }
}
