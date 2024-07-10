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


    public List<Tarjeta> obtenerTarjetas() {
        // 00084020 metodo para otener las tarjetas
        List<Tarjeta> tarjetas = new ArrayList<>();
        // 00084020 crea nueva lista para tarjetas
        try {
            Statement stmt = DatabaseConnection.getConnection().createStatement();// 00084020 se crea un statement
            String query = "SELECT ta.id, numero, fechaVencimiento, codigo, facilitador_id, cliente_id, cl.nombre, fa.tipo, ta.tipo_tarjeta " + // 00084020 se crea query
                    "FROM tarjeta ta " +// 00084020 se crea query
                    "INNER JOIN cliente cl ON ta.cliente_id = cl.id " + // 00084020 se crea query
                    "INNER JOIN facilitador fa ON ta.facilitador_id = fa.id ;";// 00084020 se crea query

            ResultSet rs = stmt.executeQuery(query); // 00084020 se ejecuta statement y se guarda Resultset

            leerTarjetas(tarjetas, rs); // 00084020 se lee los datos de resultset
        } catch (SQLException e) {// 00084020 hace catch la SQLEexception
            e.printStackTrace();// 00084020 se imprime el stack trace
        } finally { // 00084020 cuando se termina el bloque try-catch se ejecutara esto 
            DatabaseConnection.closeConnection();// 00084020 se llama a la clase databaseconnection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return tarjetas; // 00084020 se devuelven las tarjetass
    }

    public List<Tarjeta> buscarTarjetas(String termino) {// 00084020 metodo para buscar tarjetass
        List<Tarjeta> tarjetas = new ArrayList<>();// 00084020 se crea una lista de tipo tarjetas
        try {
            String query = "SELECT  ta.id, numero, fechaVencimiento, codigo, facilitador_id, cliente_id, cl.nombre, fa.tipo, ta.tipo_tarjeta " + // 00084020 se crea query
                    "FROM tarjeta ta " + // 00084020 se crea query
                    "INNER JOIN cliente cl ON ta.cliente_id = cl.id " +// 00084020 se crea query
                    "INNER JOIN facilitador fa ON ta.facilitador_id = fa.id " + // 00084020 se crea query
                    "WHERE numero LIKE ? " +// 00084020 se crea query
                    "OR codigo LIKE ?" + // 00084020 se crea query
                    "OR cl.nombre LIKE ?" + // 00084020 se crea query
                    "OR fa.tipo LIKE ?" + // 00084020 se crea query
                    "OR ta.tipo_tarjeta LIKE ?;";// 00084020 se crea query

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00084020 se crea un prepared statement y se le pasa la query

            pst.setString(1, "%" + termino + "%");// 00084020 se asigna parametro al prepared statement
            pst.setString(2, "%" + termino + "%");// 00084020 se asigna parametro al prepared statement
            pst.setString(3, "%" + termino + "%");// 00084020 se asigna parametro al prepared statement
            pst.setString(4, "%" + termino + "%");// 00084020 se asigna parametro al prepared statement
            pst.setString(5, "%" + termino + "%");// 00084020 se asigna parametro al prepared statement

            ResultSet rs = pst.executeQuery(); // 00084020 se ejecuta statement y se guarda en ResultSet

            leerTarjetas(tarjetas, rs); // 00084020 se lee los datos Resultset
        } catch (SQLException e) { // 00084020 hace catch la SQLEexception
            e.printStackTrace();// 00084020 se imprime el stack trace
        } finally {// 00084020 cuando se termina el bloque try-catch se ejecutara esto 
            DatabaseConnection.closeConnection(); // 00084020 se llama la clase databaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return tarjetas; // 00084020 se devuelven las tarjetas
    }

    private void leerTarjetas(List<Tarjeta> tarjetas, ResultSet rs) throws SQLException { // 00084020 metodo para leer resultset
        while (rs.next()) {// 00084020 se ejecuta mientras el resultset tenga datos
            Tarjeta tarjeta = new Tarjeta();// 00084020 se ejecuta la instancia tarjeta
            tarjeta.setId(rs.getInt("id"));// 00084020 se asigna el id
            tarjeta.setNumero(rs.getString("numero"));// 00084020 se asigna el numero
            tarjeta.setFechaVencimiento(dateAString(rs.getDate("fechaVencimiento")));// 00084020 se asigna la fecha de vencimiento
            tarjeta.setCodigo(rs.getString("codigo")); // 00084020 se asigna el codigo
            tarjeta.setFacilitadorId(rs.getInt("facilitador_id")); // 00084020 se asigna el facitador_id
            tarjeta.setClienteId(rs.getInt("cliente_id")); // 00084020 se asigna el cliente_id
            tarjeta.setNombreCliente(rs.getString("nombre"));// 00084020 se asigna el nombre
            tarjeta.setNombreFacilitador(rs.getString("tipo"));// 00084020 se asigna el tipo
            tarjeta.setTipoTarjeta(rs.getString("tipo_tarjeta"));// 00084020 se asigna el tipo de tarjeta
            tarjetas.add(tarjeta); // 00084020 se agrega una tarjeta
        }
    }

    public boolean persistirTarjeta(Tarjeta tarjeta) { // 00084020 metodo para guardar / actualizar la compra
        try {
            String query; // 00084020 string para el query
            int result;// 00084020 resultado que determina si salio bien o mal la tarjeta
            if(Objects.isNull(tarjeta.getId())) {// 00084020 si id es nulo, es un insert
                query = "INSERT INTO tarjeta (numero, fechaVencimiento, codigo, facilitador_id, cliente_id, tipo_tarjeta) VALUES (?, ?, ?, ?, ?, ?);"; // 00084020 query para insert
            } else {
                query = "UPDATE tarjeta SET numero = ?, fechaVencimiento = ?, codigo = ?, facilitador_id = ?, cliente_id = ?, tipo_tarjeta = ? WHERE id = ?;";// 00084020 query para update
            }

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);// 00084020  preparedstatement con la query
            pst.setString(1, tarjeta.getNumero());// 00084020 se asigna parametro 1 numero
            pst.setDate(2, java.sql.Date.valueOf(stringALocalDate(tarjeta.getFechaVencimiento()))); // 00084020 se asigna el parametro 2 fecha vencimiento
            pst.setString(3, tarjeta.getCodigo());// 00084020 se asigna el parametro 3 codigo
            pst.setInt(4, tarjeta.getFacilitadorId());// 00084020 se asigna el parametro 4 facilitador
            pst.setInt(5, tarjeta.getClienteId());// 00084020 se asigna el parametro 5 cliente
            pst.setString(6, tarjeta.getTipoTarjeta());// 00084020 se asigna el parametro 6 tipo tarjeta

            if(Objects.nonNull(tarjeta.getId())) {// 00084020 si no es nulo, es un update
                pst.setInt(7, tarjeta.getId()); // 00084020 se asigna el parametro 7 id
            }

            result = pst.executeUpdate();// 00084020 se ejecuta el prepared statement

            if(result > 0) { // 00084020 si el resultset fue exitoso es mayor a 0
                System.out.println("EXITO");// 00084020 se imprime en consola
                return true;// 00084020 se devuelve el exito
            } else {
                System.out.println("FRACASO");// 00084020 se imprime en consola
                return false;// 00084020 se  devuelve el fracaso
            }

        } catch (SQLException e) { // 00084020 se hace catch la exception  SQLException
            e.printStackTrace(); // 00084020 Se imprime el stack trace
        } finally { // 00084020 cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00084020 se llama a la clase databaseconnection y se cierra la coneccion con el metodo estatico closeConnection()
        }
        return true;// 00084020 se devuelve true si todo salio bien
    }

    public boolean eliminarTarjeta(Integer idTarjeta) {// 00084020 metodo para eliminar la compra
        try {
            String query = "DELETE FROM tarjeta WHERE id = ?";// 00084020 string para la query delete
            int result;// 00084020 resultado que determina si salio bien o mal la transaccion
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);// 00084020 preparedstatemen con la query

            pst.setInt(1, idTarjeta);// 00084020 se asigna parametro 1 id

            result =  pst.executeUpdate(); // 00084020 se ejecuta el prepared statement

            if(result > 0) {// 00084020 si el result fue exitoso es mayor a 0
                System.out.println("EXITO"); // 00084020 se imprime en consola
                return true; // 00084020 se devuelve el exito
            } else {
                System.out.println("FRACASO");// 00084020 se imprime en consola
                return false;// 00084020 se devuelve el fracaso
            }
        } catch (SQLException e) {// 00084020 se asigna la execpcion SQLException
            e.printStackTrace();// 00084020 se imprime el stack trace
        } finally {// 00084020 cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection();// 00084020 se llama la clase  databaseconnection y se cierra la conexion con el metodo estatico closeConnection()
        }
        return true; // 00084020si todo salio bien, devuelve true
    }

    public String dateAString(Date fecha) {
        // 00084020 metodo para convertir fecha a string
        SimpleDateFormat formato = new SimpleDateFormat("MM/yy");
        // 00084020 creacion formato fecha
        return formato.format(fecha);
        // 00084020 formateo y retorno de la fecha como string
    }

    private LocalDate stringALocalDate(String fechaString) {// 00084020 metodo para convertir string a localdate
        LocalDate fecha = LocalDate.parse("01/" + fechaString, DateTimeFormatter.ofPattern("dd/MM/yy"));
        // 00084020 convierte string a localdate
        return fecha.with(TemporalAdjusters.firstDayOfMonth());
        // 00084020 retorna el primer dia del mes
    }
}
