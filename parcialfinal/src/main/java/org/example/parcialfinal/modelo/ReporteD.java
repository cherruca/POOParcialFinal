package org.example.parcialfinal.modelo;

import org.example.parcialfinal.controllador.DatabaseConnection;

import java.io.File;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReporteD implements ReporteInterfaz {
    private String tipo; // 00191322 Atributo que almacena el tipo de Tarjeta

    public ReporteD() { // 00191322 constructor sin parametros
    }

    public ReporteD(String tipo) { // 00191322 constructor con parametros de la classe
        this.tipo = tipo;
    }

    public String getTipo() { // 00191322 Metodo obtener tipo
        return tipo; // 00191322 retornar tipo
    }

    public void setTipo(String tipo) { // 00191322 Metodo setear tipo
        this.tipo = tipo; // 00191322 se setea tipo
    }

    @Override // 00402523 sobrescribir el metodo de la interfaz
    public void generarConsulta(ReporteParametro reporteParametro) { // 00402523 metodo para generar la consulta mysql
        try { // 00402523 inicia try catch para la consulta mysql
            String query = "SELECT c.id AS cliente_id, c.nombre AS nombre_cliente, COUNT(co.id) AS cantidad_compras, SUM(co.monto) AS total_gastado FROM cliente c JOIN tarjeta t ON c.id = t.cliente_id JOIN facilitador f ON t.facilitador_id = f.id JOIN compra co ON t.id = co.tarjeta_id WHERE c.facilitador_id = ? GROUP BY c.id, c.nombre;"; // 00402523 se crea la consulta
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query); // 00402523 se crea la consulta preparada con la clase de la conexion a la base

            tipo = reporteParametro.getTipo(); // 00191322 se setea el tipo de tarjeta al atributo de la clase

            pst.setInt(1, reporteParametro.getId()); // 00402523 se envia parametro

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
            StringBuilder texto = new StringBuilder(); // 00191322 se crea un String Builder para agregar string
            StringBuilder compras = new StringBuilder(); // 00191322 se crea un String Builder para agregar string, listado de tarjetas de credito

            texto.append("Facilitador de Tarjeta: ").append(tipo).append("\n"); // 00191322 se agrega la informacion de facilitador de tarjeta
            texto.append("Compras: \n"); // 00191322 se agrega el titulo de compras
            texto.append("\t").append("Nombre").append("\t").append("Cantidad de Compras").append("\t").append("Total Gastado").append("\n\n"); // 00191322 Se agrega los titulos de las tablas
            while (rs.next()) {// 00402523 inicia el bucle para recorrer el resultset
                compras.append("\t").append(rs.getString("nombre_cliente")).append("\t").append(rs.getString("cantidad_compras")).append("\t").append(rs.getString("total_gastado")).append("\n"); // 00191322 Se agrega la info de los clientes
            }

            texto.append(compras); // 00191322 Se agrega las compras al texto

            guardarArchivo(texto.toString()); // 00402523 se envia el texto del reporte al metodo para guardar archivo

            return texto.toString(); // 00402523 se retorna el texto del reporte
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
//                texto += line + "";
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

            File archivoRuta = new File(".\\reportes" + "\\" + "reporte-C-" + timeStamp + ".txt"); // 00402523 direccion y nombre para guardar archivo
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
