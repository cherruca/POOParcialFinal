package org.example.parcialfinal.modelo;

import org.example.parcialfinal.controllador.DatabaseConnection;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReporteA implements ReporteInterfaz {
    @Override
    public void generarConsulta(ReporteParametro reporteParametro) {
        try {
            String query = "SELECT compra.fecha, compra.monto, tarjeta.numero, cliente.nombre FROM compra LEFT JOIN tarjeta ON tarjeta.id = compra.tarjeta_id LEFT JOIN cliente ON cliente.id = tarjeta.cliente_id WHERE cliente.id = ? AND compra.fecha BETWEEN ? AND ?";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(query);

            pst.setInt(1, reporteParametro.getId());
            pst.setDate(2, reporteParametro.getInicio());
            pst.setDate(3, reporteParametro.getFin());
            System.out.println(pst);
            ResultSet rs = pst.executeQuery();

            generarReporte(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @Override
    public String generarReporte(ResultSet rs) {
        try {
            String texto = "";
            while (rs.next()) {
                String fecha = String.valueOf(rs.getDate("fecha"));
                String numero = rs.getString("numero");
                Float monto = rs.getFloat("monto");
                String cliente = rs.getString("nombre");


                texto += fecha + " " + numero + " " + monto + " " + cliente + "\n";


            }

            guardarArchivo(texto);

            return texto;
        } catch (Exception e) {

        }
        return "vacio";
    }

    @Override
    public String abrirArchivo(File selectedFile) {


        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
//            txtArchivoNombre.setText(selectedFile.getName());
            String line;
            String texto = "";
// Lee el archivo línea por línea
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                texto += line + "\n";
            }
            return texto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("archivo abrir");
        return "vacio";
    }

    @Override
    public void guardarArchivo(String texto) {
        try {
            //Mostrar el cuadro de diálogo y obtener el directorio seleccionado
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

            File archivoRuta = new File(".\\reportes" + "\\" + "reporte-A-" + timeStamp + ".txt");
            System.out.println(archivoRuta);
            FileWriter escritor = new FileWriter(archivoRuta);
            System.out.println(archivoRuta);
            escritor.write(texto);
            escritor.close();
            System.out.println("archivo guardado");

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}
