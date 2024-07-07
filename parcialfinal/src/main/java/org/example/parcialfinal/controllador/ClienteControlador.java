package org.example.parcialfinal.controllador;

import org.example.parcialfinal.modelo.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteControlador {
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Statement stmt = DatabaseConnection.getConnection().createStatement();
            String query = "SELECT id, nombre, telefono, direccion FROM cliente";

            ResultSet rs = stmt.executeQuery(query);

            leerClientes(clientes, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return clientes;
    }

    public List<Cliente> buscarClientes(String termino) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            String query = "SELECT id, nombre, telefono, direccion " +
                    "FROM cliente " +
                    "WHERE nombre LIKE ? " +
                    "OR telefono LIKE ? " +
                    "OR direccion LIKE ?;";

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setString(1, "%" + termino + "%");
            pst.setString(2, "%" + termino + "%");
            pst.setString(3, "%" + termino + "%");

            ResultSet rs = pst.executeQuery();

            leerClientes(clientes, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return clientes;
    }

    private void leerClientes(List<Cliente> clientes, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setDireccion(rs.getString("direccion"));
            clientes.add(cliente);
        }
    }

    public void persistirCliente(Cliente cliente) {
        try {
            String query;
            int result;
            if(Objects.isNull(cliente.getId())) {
                query = "INSERT INTO cliente (nombre, telefono, direccion) VALUES (?, ?, ?);";
            } else {
                query = "UPDATE cliente SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?;";
            }

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getTelefono());
            pst.setString(3, cliente.getDireccion());
            if(Objects.nonNull(cliente.getId())) {
                pst.setInt(4, cliente.getId());
            }

            result = pst.executeUpdate();

            if(result > 0) {
                System.out.println("EXITO");
            } else {
                System.out.println("FRACASO");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void eliminarCliente(Integer idCliente) {
        try {
            String query = "DELETE FROM cliente WHERE id = ?";

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setInt(1, idCliente);

            pst.executeUpdate();

            if(pst.executeUpdate() > 0) {
                System.out.println("EXITO");
            } else {
                System.out.println("FRACASO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}
