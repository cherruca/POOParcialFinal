package org.example.parcialfinal.controllador;

import org.example.parcialfinal.modelo.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteControlador { // 00191322 Clase cliente controlador
    public Cliente buscarCliente(Integer id) { // 00191322 Metodo para buscar cliente
        Cliente cliente = new Cliente(); // 00191322 Se crea una instancia de tipo Cliente
        try {
            String query = "SELECT id, nombre, telefono, direccion FROM cliente WHERE id = ?; "; // 00191322 Query para select del objeto

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00191322 Se crea un prepared Stament y se le pasa la query

            pst.setInt(1, id); // 00191322 Se setea el parametro del prepared Statement, en este caso el id

            ResultSet rs = pst.executeQuery(); // 00191322 Se ejecuta el prepared statement

            while (rs.next()) { // 00191322 si tiene datos el resultset, esto se ejecutara por cada uno
                cliente.setId(rs.getInt("id")); // 00191322 Se le asigna el id al objeto cliente
                cliente.setNombre(rs.getString("nombre")); // 00191322 Se le asigna el nombre al objeto cliente
                cliente.setTelefono(rs.getString("telefono")); // 00191322 Se le asigna el telefono al objeto cliente
                cliente.setDireccion(rs.getString("direccion")); // 00191322 Se le asigna la direccion al objeto cliente
            }
        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            e.printStackTrace(); // 00191322 Se imprime el Stack Trace
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return cliente; // 00191322 Se retorna la instancia de cliente
    }

    public List<Cliente> obtenerClientes() { // 00191322 metodo para obtener clientes
        List<Cliente> clientes = new ArrayList<>(); // 00191322 se crea una lista de tipo cliente
        try {
            Statement stmt = DatabaseConnection.getConnection().createStatement(); // 00191322 se crea Statement
            String query = "SELECT id, nombre, telefono, direccion FROM cliente"; // 00191322 se crea query

            ResultSet rs = stmt.executeQuery(query); // 00191322 se ejecuta statement y se guarda en Resultset

            leerClientes(clientes, rs); // 00191322 se lee los datos del Resultset
        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            e.printStackTrace(); // 00191322 Se imprime el Stack Trace
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return clientes; // 00191322 se devuelven los clientes
    }

    public List<Cliente> buscarClientes(String termino) { // 00191322 metodo para buscar clientes
        List<Cliente> clientes = new ArrayList<>(); // 00191322 se crea una lista de tipo cliente
        try {
            String query = "SELECT id, nombre, telefono, direccion " + // 00191322 se crea query
                    "FROM cliente " + // 00191322 query desde cliente
                    "WHERE nombre LIKE ? " + // 00191322 query con nombre
                    "OR telefono LIKE ? " + // 00191322 query con telefono
                    "OR direccion LIKE ?;"; // 00191322 query con direccion

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00191322 Se crea un prepared Stament y se le pasa la query

            pst.setString(1, "%" + termino + "%"); // 00191322 Se asigna parametro al prepared Statement
            pst.setString(2, "%" + termino + "%"); // 00191322 Se asigna parametro al prepared Statement
            pst.setString(3, "%" + termino + "%"); // 00191322 Se asigna parametro al prepared Statement

            ResultSet rs = pst.executeQuery(); // 00191322 se ejecuta statement y se guarda en Resultset

            leerClientes(clientes, rs); // 00191322 se lee los datos del Resultset
        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            e.printStackTrace(); // 00191322 Se imprime el Stack Trace
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return clientes; // 00191322 se devuelven los clientes
    }

    private void leerClientes(List<Cliente> clientes, ResultSet rs) throws SQLException { // 00191322 Metodo para leer los resultset
        while (rs.next()) { // 00191322 se ejecuta mientras hayan datos
            Cliente cliente = new Cliente(); // 00191322 instancia de cliente
            cliente.setId(rs.getInt("id")); // 00191322 se setea el id
            cliente.setNombre(rs.getString("nombre")); // 00191322 se setea el nombre
            cliente.setTelefono(rs.getString("telefono")); // 00191322 se setea el telefono
            cliente.setDireccion(rs.getString("direccion")); // 00191322 se setea la direccion
            clientes.add(cliente); // 00191322 se agrega un cliente a la lista
        }
    }

    public boolean persistirCliente(Cliente cliente) { // 00191322 metodo para guardar/actualizar el cliente
        try {
            String query; // 00191322 string para la query
            int result; // 00191322 resultado que determina si salio bien o mal la transaccion
            if(Objects.isNull(cliente.getId())) { // 00191322 si id es nulo, es un insert
                query = "INSERT INTO cliente (nombre, telefono, direccion) VALUES (?, ?, ?);"; // 00191322 query para insert
            } else {
                query = "UPDATE cliente SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?;"; // 00191322 query para update
            }

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00191322 preparedstatement con la query
            pst.setString(1, cliente.getNombre()); // 00191322 se setea parametro 1 nombre
            pst.setString(2, cliente.getTelefono()); // 00191322 se setea parametro 2 telefono
            pst.setString(3, cliente.getDireccion()); // 00191322 se setea parametro 3 direccion
            if(Objects.nonNull(cliente.getId())) { // 00191322 si no es nulo, es un update
                pst.setInt(4, cliente.getId()); // 00191322 se setea parametro 4 id
            }

            result = pst.executeUpdate(); // 00191322 se ejecuta el prepared statement

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

    public boolean eliminarCliente(Integer idCliente) { // 00191322 metodo para eliminar el cliente
        try {
            String query = "DELETE FROM cliente WHERE id = ?"; // 00191322 string para la query delete
            int result; // 00191322 resultado que determina si salio bien o mal la transaccion
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00191322 preparedstatement con la query

            pst.setInt(1, idCliente); // 00191322 se setea parametro 1 id

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
}
