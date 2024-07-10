package org.example.parcialfinal.controllador;

import org.example.parcialfinal.modelo.Facilitador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FacilitadorControlador { // 00051316 Declara la clase FacilitadorControlador
    public Facilitador obtenerFacilitador(Integer id) { // 00051316 Declara el metodo obtenerFacilitador que devuelve un objeto de tipo Facilitador y recibe un parametro id de tipo Integer
        Facilitador facilitador = new Facilitador(); // 00051316 Crea un nuevo objeto de tipo Facilitador

        try { // 00051316 Inicia un bloque try para manejar excepciones
            // 00051316 Preparar la consulta SQL para obtener un facilitador por su ID
            String query = "SELECT id, tipo " + // 00051316 Define la consulta SQL para seleccionar el id y tipo de la tabla facilitador
                    "FROM facilitador " + // 00051316 tabla donde se hara la busquedad
                    "WHERE id = ? ;"; // 00051316 Condicional para el id

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00051316 Prepara la declaracion SQL usando la conexion a la base de datos obtenida de DatabaseConnection

            pst.setInt(1, id); // 00051316 Establece el parametro para el id en la consulta preparada

            ResultSet rs = pst.executeQuery(); // 00051316 Ejecuta la consulta y guarda el resultado en rs (ResultSet)

            while (rs.next()) { // 00051316 Captura excepciones de tipo SQLException
                facilitador.setId(rs.getInt("id")); // 00051316 Establece el id del facilitador con el valor obtenido de la columna "id" en rs
                facilitador.setTipo(rs.getString("tipo")); // 00051316 Establece el tipo del facilitador con el valor obtenido de la columna "tipo" en rs
            }
        } catch (SQLException e) { // 00051316 Captura excepciones de tipo SQLException
            e.printStackTrace(); // 00051316 Imprime el rastreo de la excepcion
        } finally { // 00051316 Bloque finally que siempre se ejecuta despues del try-catch
            DatabaseConnection.closeConnection(); // 00051316 Llama al método closeConnection de DatabaseConnection para cerrar la conexion a la base de datos

        }
        return facilitador; // 00051316 Devuelve el objeto facilitador
    }

    public List<Facilitador> obtenerFacilitadores() { // 00051316 Declara el metodo obtenerFacilitadores que devuelve una lista de objetos Facilitador
        List<Facilitador> facilitadores = new ArrayList<>(); // 00051316 Crea una nueva lista de objetos Facilitador
        try { // 00051316 Inicia un bloque try para manejar excepciones
            String query = "SELECT id, tipo FROM facilitador;"; // 00051316 Define la consulta SQL para seleccionar el id y tipo de todos los facilitadores
            Statement stmt = DatabaseConnection.getConnection().createStatement(); // 00051316 Crea un objeto Statement usando la conexion a la base de datos obtenida de DatabaseConnection


            ResultSet rs = stmt.executeQuery(query); // 00051316 Ejecuta la consulta y guarda el resultado en rs (ResultSet)

            while (rs.next()) { // 00051316 Itera sobre cada fila del resultado
                Facilitador facilitador = new Facilitador(); // 00051316 Crea un nuevo objeto de tipo Facilitador
                facilitador.setId(rs.getInt("id")); // 00051316 Establece el id del facilitador con el valor obtenido de la columna "id" en rs
                facilitador.setTipo(rs.getString("tipo")); // 00051316 Establece el tipo del facilitador con el valor obtenido de la columna "tipo" en rs
                facilitadores.add(facilitador); // 00051316 Agrega el facilitador a la lista facilitadores
            }

        } catch (SQLException e) { // 00051316 Captura excepciones de tipo SQLException
            e.printStackTrace(); // 00051316 Imprime el rastreo de la excepcion
        } finally { // 00051316 Bloque finally que siempre se ejecuta despues del try-catch
            DatabaseConnection.closeConnection(); // 00051316 Llama al metodo closeConnection de DatabaseConnection para cerrar la conexion a la base de datos
        }
        return facilitadores; // 00051316 Devuelve la lista facilitadores que contiene todos los objetos Facilitador obtenidos de la base de datos
    }
}