package org.example.parcialfinal.modelo;

import java.util.Date;

public class ReporteParametro {
    private int id;
    private Date inicio;
    private Date fin;
    private String tipo;

    public ReporteParametro(int id, Date inicio, Date fin, String tipo) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.tipo = tipo;
    }

    public ReporteParametro(int id, Date inicio, Date fin) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
    }

    public ReporteParametro(int id, Date inicio) {
        this.id = id;
        this.inicio = inicio;
    }

    public ReporteParametro(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getInicio() {
        return (java.sql.Date) inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public java.sql.Date getFin() {
        return (java.sql.Date) fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
