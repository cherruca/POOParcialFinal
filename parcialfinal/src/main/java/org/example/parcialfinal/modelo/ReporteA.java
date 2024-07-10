package org.example.parcialfinal.modelo;

import org.example.parcialfinal.controllador.DatabaseConnection;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReporteA implements ReporteInterfaz { // 00402523 creación de la clase
    @Override // 00402523 sobrescribir el metodo de la interfaz
    public void generarConsulta(ReporteParametro reporteParametro) { // 00402523 metodo para generar la consulta mysql
        try { // 00402523 inicia try catch para la consulta mysql
            String query = "SELECT compra.fecha, compra.monto, tarjeta.numero, cliente.nombre FROM compra LEFT JOIN tarjeta ON tarjeta.id = compra.tarjeta_id LEFT JOIN cliente ON cliente.id = tarjeta.cliente_id WHERE cliente.id = ? AND compra.fecha BETWEEN ? AND ?"; // 00402523 se crea la consulta
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00402523 se crea la consulta preparada con la clase de la conexion a la base

            pst.setInt(1, reporteParametro.getId()); // 00402523 se envia parametro
            pst.setDate(2, reporteParametro.getInicio()); // 00402523 se envia parametro
            pst.setDate(3, reporteParametro.getFin()); // 00402523 se envia parametro

            ResultSet rs = pst.executeQuery(); // 00402523 se ejecuta la sentencia preparada

            generarReporte(rs); // 00402523 se envia la sentencia preparada a la funcion generar reporte
        } catch (SQLException e) { // 00402523 catch por si ocurre un error SQL
            e.printStackTrace(); // 00402523 se imprime el error
        } finally { // 00402523 se finaliza el try catch
            DatabaseConnection.closeConnection(); // 00402523 se cierra la conexion
        }
    }

    @Override // 00402523 sobrescribir el metodo de la interfaz
    public String generarReporte(ResultSet rs) { // 00402523 metodo para generar el texto del reporte
        try { // 00402523 inicia try catch para la consulta mysql
            String texto = ""; // 00402523 se inicialia la variable para devolver
            while (rs.next()) { // 00402523 inicia el bucle para recorrer el resultset
                String fecha = String.valueOf(rs.getDate("fecha")); // 00402523 se guarda dato en variable
                String numero = rs.getString("numero"); // 00402523 se guarda dato en variable
                Float monto = rs.getFloat("monto");// 00402523 se guarda dato en variable
                String cliente = rs.getString("nombre"); // 00402523 se guarda dato en variable


                texto += fecha + " " + numero + " " + monto + " " + cliente + "\n"; // 00402523 se concatenan los datos del resultset en una variable


            }

            guardarArchivo(texto); // 00402523 se envia el texto del reporte al metodo para guardar archivo

            return texto; // 00402523 se retorna el texto del reporte
        } catch (Exception e) { // 00402523 catch por si ocurre un error SQL
            e.printStackTrace(); // 00402523 se imprime el error
        }
        return "vacio"; // 00402523 si ocurre un error se retorna un reporte vacio
    }

    @Override // 00402523 se sobrescribe metodo de interfaz
    public String abrirArchivo(File selectedFile) { // 00402523 el metodo no se ocupa
//
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
////            txtArchivoNombre.setText(selectedFile.getName());
//            String line;
//            String texto = "";
//// Lee el archivo línea por línea
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//                texto += line + "\n";
//            }
//            return texto;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("archivo abrir");
        return null; // 00402523 el metodo no se ocupa
    }

    @Override // 00402523 se sobreescribe el metodo de interfaz
    public void guardarArchivo(String texto) { // 00402523 metodo para guardar el reporte en un .txt
        try { // 00402523 inicia try catch para guardar archivo

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); // 00402523 se genera fecha y hora para guardar reporte

            File archivoRuta = new File(".\\reportes" + "\\" + "reporte-A-" + timeStamp + ".txt"); // 00402523 direccion y nombre para guardar archivo
            System.out.println(archivoRuta); // 00402523 se imprime la ruta del archivo en consola
            FileWriter escritor = new FileWriter(archivoRuta); // 00402523 se crea el archivo

            escritor.write(texto); // 00402523 se escribe el reporte en el archivo
            escritor.close(); // 00402523 se cierra el archivo
            System.out.println("archivo guardado"); // 00402523 se imprime mensaje en consola

        } catch (Exception e) { // 00402523 catch por si ocurre error
            System.out.println("ERROR: " + e); // 00402523 se imprime el error
        }
    }
}
