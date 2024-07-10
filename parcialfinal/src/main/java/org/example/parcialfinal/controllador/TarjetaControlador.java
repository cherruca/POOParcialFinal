package org.example.parcialfinal.controllador;

import org.example.parcialfinal.modelo.Cliente;
import org.example.parcialfinal.modelo.Tarjeta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TarjetaControlador {
    // 00084020 clase publica TarjetaControlador
    public Tarjeta obtenerTarjeta(int id) {
        // 00084020 metodo publico para obtener tarjeta 
        Tarjeta tarjeta = new Tarjeta();
        // 00084020 crea nueva instancia tarjeta
        try {
            // 00084020 try catch para excepciones
            String query = "SELECT ta.id, numero, fechaVencimiento, codigo, facilitador_id, cliente_id, cl.nombre, fa.tipo, ta.tipo_tarjeta " +// 00084020 query para select del objeto
                    "FROM tarjeta ta " + // 00084020 query para select del objeto
                    "INNER JOIN cliente cl ON ta.cliente_id = cl.id " +// 00084020 query para select del objeto
                    "INNER JOIN facilitador fa ON ta.facilitador_id = fa.id " +// 00084020 query para select del objeto
                    "WHERE ta.id = ? ;";// 00084020 query para select del objeto

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00084020 Se crea un prepared Stament y se le pasa la query
            pst.setInt(1, id); // 00084020 Se setea el parametro del prepared Statement, en este caso el id

            ResultSet rs = pst.executeQuery(); // 00084020 Se ejecuta el prepared statement

            while (rs.next()) { // 00084020 si tiene datos el resultset, esto se ejecutara por cada uno
                tarjeta.setId(rs.getInt("id"));// 00084020 Se le asigna el id al objeto tarjeta
                tarjeta.setNumero(rs.getString("numero"));  // 00084020 Se le asigna el numero al objeto tarjeta
                tarjeta.setFechaVencimiento(dateAString(rs.getDate("fechaVencimiento")));  // 00084020 Se le asigna la fecha vencimiento al objeto tarjeta
                tarjeta.setCodigo(rs.getString("codigo")); // 00084020 Se le asigna el codigo al objeto tarjeta
                tarjeta.setFacilitadorId(rs.getInt("facilitador_id"));  // 00084020 Se le asigna el facilitador id del objeto tarjeta
                tarjeta.setClienteId(rs.getInt("cliente_id"));  // 00084020 Se le asigna el cliente id del objeto tarjeta
                tarjeta.setNombreCliente(rs.getString("nombre")); // 00084020 Se le asigna el nombre al objeto tarjeta
                tarjeta.setNombreFacilitador(rs.getString("tipo")); // 00084020 Se le asigna el tipo al objeto tarjeta
                tarjeta.setTipoTarjeta(rs.getString("tipo_tarjeta")); // 00084020 Se le asigna el tipo de tarjeta al objeto tarjeta
            }
        } catch (SQLException e) {  // 00084020 Se hace el catch de la excepion SQLException
            e.printStackTrace();  // 00084020 se imprime el stach trace
        } finally { // 00084020 cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00084020  Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return tarjeta; //00084020 Se retorna la instancia de tarjeta
    }

    public List<String> obtenerTodosNumerosTarjeta() { // 00084020 metodo para obtener numers tarjeta
        List<String> numerosTarjeta = new ArrayList<>(); // 00084020 se crea una lista para numeros tarjeta
        try {
            String query = "SELECT numero FROM tarjeta;"; // 00084020 se crea query
            Statement stmt = DatabaseConnection.getConnection().createStatement(); // 00084020 se crea statement

            ResultSet rs = stmt.executeQuery(query); // 00084020 se ejecuta statement y se guarda en resultset

            while (rs.next()) {
                // 00084020 iteracion sobre el resultado de la consulta
                numerosTarjeta.add(rs.getString("numero"));
                // 00084020 agrega numero tarjeta a lista
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 00084020 manejo excepcion SQL
        } finally {
            DatabaseConnection.closeConnection();
            // 00084020 cierra conexion a la base
        }
        return numerosTarjeta;
        // 00084020 retorna la lista numeros tarjeta
    }

    public String obtenerNumeroTarjetaPorId(int id) {
        StringBuilder numero = new StringBuilder();
        try {
            String query = "SELECT numero FROM tarjeta WHERE id = ?;";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery(query);

            while (rs.next()) {
                numero.append(rs.getString("numero"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return numero.toString();
    }

    public List<Tarjeta> obtenerTarjetas() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        try {
            Statement stmt = DatabaseConnection.getConnection().createStatement();
            String query = "SELECT ta.id, numero, fechaVencimiento, codigo, facilitador_id, cliente_id, cl.nombre, fa.tipo, ta.tipo_tarjeta " +
                    "FROM tarjeta ta " +
                    "INNER JOIN cliente cl ON ta.cliente_id = cl.id " +
                    "INNER JOIN facilitador fa ON ta.facilitador_id = fa.id ;";

            ResultSet rs = stmt.executeQuery(query);

            leerTarjetas(tarjetas, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return tarjetas;
    }

    public List<Tarjeta> buscarTarjetas(String termino) {
        List<Tarjeta> tarjetas = new ArrayList<>();
        try {
            String query = "SELECT  ta.id, numero, fechaVencimiento, codigo, facilitador_id, cliente_id, cl.nombre, fa.tipo, ta.tipo_tarjeta " +
                    "FROM tarjeta ta " +
                    "INNER JOIN cliente cl ON ta.cliente_id = cl.id " +
                    "INNER JOIN facilitador fa ON ta.facilitador_id = fa.id " +
                    "WHERE numero LIKE ? " +
                    "OR codigo LIKE ?" +
                    "OR cl.nombre LIKE ?" +
                    "OR fa.tipo LIKE ?" +
                    "OR ta.tipo_tarjeta LIKE ?;";

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setString(1, "%" + termino + "%");
            pst.setString(2, "%" + termino + "%");
            pst.setString(3, "%" + termino + "%");
            pst.setString(4, "%" + termino + "%");
            pst.setString(5, "%" + termino + "%");

            ResultSet rs = pst.executeQuery();

            leerTarjetas(tarjetas, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return tarjetas;
    }

    private void leerTarjetas(List<Tarjeta> tarjetas, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setId(rs.getInt("id"));
            tarjeta.setNumero(rs.getString("numero"));
            tarjeta.setFechaVencimiento(dateAString(rs.getDate("fechaVencimiento")));
            tarjeta.setCodigo(rs.getString("codigo"));
            tarjeta.setFacilitadorId(rs.getInt("facilitador_id"));
            tarjeta.setClienteId(rs.getInt("cliente_id"));
            tarjeta.setNombreCliente(rs.getString("nombre"));
            tarjeta.setNombreFacilitador(rs.getString("tipo"));
            tarjeta.setTipoTarjeta(rs.getString("tipo_tarjeta"));
            tarjetas.add(tarjeta);
        }
    }

    public boolean persistirTarjeta(Tarjeta tarjeta) {
        try {
            String query;
            int result;
            if(Objects.isNull(tarjeta.getId())) {
                query = "INSERT INTO tarjeta (numero, fechaVencimiento, codigo, facilitador_id, cliente_id, tipo_tarjeta) VALUES (?, ?, ?, ?, ?, ?);";
            } else {
                query = "UPDATE tarjeta SET numero = ?, fechaVencimiento = ?, codigo = ?, facilitador_id = ?, cliente_id = ?, tipo_tarjeta = ? WHERE id = ?;";
            }

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);
            pst.setString(1, tarjeta.getNumero());
            pst.setDate(2, java.sql.Date.valueOf(stringALocalDate(tarjeta.getFechaVencimiento())));
            pst.setString(3, tarjeta.getCodigo());
            pst.setInt(4, tarjeta.getFacilitadorId());
            pst.setInt(5, tarjeta.getClienteId());
            pst.setString(6, tarjeta.getTipoTarjeta());

            if(Objects.nonNull(tarjeta.getId())) {
                pst.setInt(7, tarjeta.getId());
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

    public boolean eliminarTarjeta(Integer idTarjeta) {
        try {
            String query = "DELETE FROM tarjeta WHERE id = ?";
            int result;
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setInt(1, idTarjeta);

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

    public String dateAString(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("MM/yy");
        return formato.format(fecha);
    }

    private LocalDate stringALocalDate(String fechaString) {
        LocalDate fecha = LocalDate.parse("01/" + fechaString, DateTimeFormatter.ofPattern("dd/MM/yy"));
        return fecha.with(TemporalAdjusters.firstDayOfMonth());
    }
}
