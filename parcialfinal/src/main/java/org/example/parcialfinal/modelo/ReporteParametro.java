package org.example.parcialfinal.modelo;

import java.util.Date;

public class ReporteParametro { // 00191322 Clase Reporte Parametro
    private int id; // 00191322 Atributo id
    private Date inicio; // 00191322 Atributo inicio
    private Date fin; // 00191322 Atributo fin
    private String tipo; // 00191322 Atributo tipo

    public ReporteParametro(int id, String tipo) { // 00191322 Constructor con paremetros
        this.id = id; // 00191322 setear id con controlador
        this.tipo = tipo; // 00191322 setear tipo con controlador
    }

    public ReporteParametro(int id, Date inicio, Date fin, String tipo) { // 00191322 Constructor con paremetros
        this.id = id; // 00191322 setear id con controlador
        this.inicio = inicio; // 00191322 setear inicio con controlador
        this.fin = fin; // 00191322 setear fin con controlador
        this.tipo = tipo; // 00191322 setear tipo con controlador
    }

    public ReporteParametro(int id, Date inicio, Date fin) { // 00191322 Constructor con paremetros
        this.id = id; // 00191322 setear id con controlador
        this.inicio = inicio; // 00191322 setear inicio con controlador
        this.fin = fin; // 00191322 setear fin con controlador
    }

    public ReporteParametro(int id, Date inicio) { // 00191322 Constructor con paremetros
        this.id = id; // 00191322 setear id con controlador
        this.inicio = inicio; // 00191322 setear inicio con controlador
    }

    public ReporteParametro(int id) { // 00191322 Constructor con 1 parametro(Id)
        this.id = id; // 00191322 setear id con controlador
    }

    public int getId() { // 00191322 Metodo obtener id
        return id; // 00191322 retornar id
    }

    public void setId(int id) { // 00191322 Metodo setear Id
        this.id = id; // 00191322 se setea Id
    }

    public java.sql.Date getInicio() { // 00191322 Metodo obtener inicio
        return (java.sql.Date) inicio; // 00191322 retornar inicio
    }

    public void setInicio(Date inicio) { // 00191322 Metodo setear inicio
        this.inicio = inicio; // 00191322 se setea inicio
    }

    public java.sql.Date getFin() { // 00191322 Metodo obtener fin
        return (java.sql.Date) fin; // 00191322 retornar fin
    }

    public void setFin(Date fin) { // 00191322 Metodo setear fin
        this.fin = fin; // 00191322 se setea fin
    }

    public String getTipo() { // 00191322 Metodo obtener tipo
        return tipo; // 00191322 retornar tipo

    }

    public void setTipo(String tipo) { // 00191322 Metodo setear tipo
        this.tipo = tipo; // 00191322 se setea tipo

    }

}