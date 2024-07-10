package org.example.parcialfinal.modelo;

import java.io.File;
import java.sql.*;

public interface ReporteInterfaz {
    public void generarConsulta(ReporteParametro reporte);

    public String generarReporte(ResultSet rs);

    public String abrirArchivo(File archivoRuta);

    public void guardarArchivo(String texto);
}
