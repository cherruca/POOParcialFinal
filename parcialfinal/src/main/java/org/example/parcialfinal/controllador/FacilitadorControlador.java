package org.example.parcialfinal.controllador;

import org.example.parcialfinal.modelo.Facilitador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FacilitadorControlador {
    public Facilitador obtenerFacilitador(Integer id) {
        Facilitador facilitador = new Facilitador();
        try {
            String query = "SELECT id, tipo " +
                    "FROM facilitador " +
                    "WHERE id = ? ;";

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                facilitador.setId(rs.getInt("id"));
                facilitador.setTipo(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return facilitador;
    }

    public List<Facilitador> obtenerFacilitadores() {
        List<Facilitador> facilitadores = new ArrayList<>();
        try {
            String query = "SELECT id, tipo FROM facilitador;";
            Statement stmt = DatabaseConnection.getConnection().createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Facilitador facilitador = new Facilitador();
                facilitador.setId(rs.getInt("id"));
                facilitador.setTipo(rs.getString("tipo"));
                facilitadores.add(facilitador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return facilitadores;
    }
}