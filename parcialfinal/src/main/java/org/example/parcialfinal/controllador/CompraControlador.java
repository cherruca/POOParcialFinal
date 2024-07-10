package org.example.parcialfinal.controllador;

import org.example.parcialfinal.modelo.Compra;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompraControlador {

    public List<Compra> obtenerCompras() {
        List<Compra> compras = new ArrayList<>();
        try {
            Statement stmt = DatabaseConnection.getConnection().createStatement();
            String query = "SELECT co.id, fecha, tarjeta_id, monto, descripcion, ta.numero " +
                    "FROM compra co " +
                    "INNER JOIN tarjeta ta ON co.tarjeta_id = ta.id;";

            ResultSet rs = stmt.executeQuery(query);

            leerCompras(compras, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return compras;
    }

    public List<Compra> buscarCompras(String termino) {
        List<Compra> compras = new ArrayList<>();
        try {
            String query = "SELECT co.id, fecha, tarjeta_id, monto, descripcion, ta.numero " +
                    "FROM compra co " +
                    "INNER JOIN tarjeta ta ON co.tarjeta_id = ta.id " +
                    "WHERE monto LIKE ? " +
                    "OR descripcion LIKE ? " +
                    "OR ta.numero LIKE ? ;";

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setString(1, "%" + termino + "%");
            pst.setString(2, "%" + termino + "%");
            pst.setString(3, "%" + termino + "%");

            ResultSet rs = pst.executeQuery();

            leerCompras(compras, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return compras;
    }

    private void leerCompras(List<Compra> compras, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setId(rs.getInt("id"));
            compra.setFechaDeCompra(rs.getString("fecha"));
            compra.setIdTarjeta(rs.getInt("tarjeta_id"));
            compra.setMonto(rs.getDouble("monto"));
            compra.setDescripcion(rs.getString("descripcion"));
            compra.setClienteTarjeta(rs.getString("numero"));
            compras.add(compra);
        }
    }

    public boolean persistirCompra(Compra compra) {
        try {
            String query;
            int result;
            if(Objects.isNull(compra.getId())) {
                query = "INSERT INTO compra (fecha, tarjeta_id, monto, descripcion) VALUES (?, ?, ?, ?);";
            } else {
                query = "UPDATE compra SET fecha = ?, tarjeta_id = ?, monto = ?, descripcion = ? WHERE id = ?;";
            }

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
            pst.setDate(1, Date.valueOf(stringALocalDate(compra.getFechaDeCompra())));
            pst.setInt(2, compra.getIdTarjeta());
            pst.setDouble(3, compra.getMonto());
            pst.setString(4, compra.getDescripcion());
            if(Objects.nonNull(compra.getId())) {
                pst.setInt(5, compra.getId());
            }

            result = pst.executeUpdate();

            if(result > 0) {
                System.out.println("EXITO");
                return true;
            } else {
                System.out.println("FRACASO");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return true;
    }

    public boolean eliminarCompra(Integer idCompra) {
        try {
            String query = "DELETE FROM compra WHERE id = ?";
            int result;
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setInt(1, idCompra);

            result =  pst.executeUpdate();

            if(result > 0) {
                System.out.println("EXITO");
                return true;
            } else {
                System.out.println("FRACASO");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return true;
    }

    private LocalDate stringALocalDate(String fechaString) {
        return LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
