package org.example.parcialfinal.modelo;

import java.io.File;
import java.sql.*;

public interface ReporteInterfaz { // 00402523 se crea una interfaz para implementar los reportes
    public void generarConsulta(ReporteParametro reporte); // 00402523 se declara metodo para sobreescribir

    public String generarReporte(ResultSet rs); // 00402523 se declara metodo para sobreescribir

    public String abrirArchivo(File archivoRuta); // 00402523 se declara metodo para sobreescribir

    public void guardarArchivo(String texto); // 00402523 se declara metodo para sobreescribir
}
