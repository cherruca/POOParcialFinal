package org.example.parcialfinal.modelo;

public class Compra { // 00191322 Clase Compra
    private Integer id; // 00191322 Atributo id
    private Double monto; // 00191322 Atributo monto
    private Integer idTarjeta; // 00191322 Atributo idTarjeta
    private String descripcion; // 00191322 Atributo descripcion
    private String fechaDeCompra; // 00191322 Atributo fechaDeCompra
    private String clienteTarjeta; // 00191322 Atributo clienteTarjeta

    public Compra() { // 00191322 Constructor vacio
    }

    public Compra(Integer id, Double monto, Integer idTarjeta, String descripcion, String fechaDeCompra) { // 00191322 Constructor con paremetros
        this.id = id; // 00191322 setear id con controlador
        this.monto = monto; // 00191322 setear monto con controlador
        this.idTarjeta = idTarjeta; // 00191322 setear idTarjeta con controlador
        this.descripcion = descripcion; // 00191322 setear descripcion con controlador
        this.fechaDeCompra = fechaDeCompra; // 00191322 setear fechaDeCompra con controlador
    }

    public Compra(Integer id, Double monto, Integer idTarjeta, String descripcion, String fechaDeCompra, String clienteTarjeta) { // 00191322 Constructor con parametros
        this.id = id; // 00191322 setear id con controlador
        this.monto = monto; // 00191322 setear monto con controlador
        this.idTarjeta = idTarjeta; // 00191322 setear idTarjeta con controlador
        this.descripcion = descripcion; // 00191322 setear descripcion con controlador
        this.fechaDeCompra = fechaDeCompra; // 00191322 setear fechaDeCompra con controlador
        this.clienteTarjeta = clienteTarjeta; // 00191322 setear clienteTarjeta con controlador
    }

    public Integer getId() { // 00191322 Metodo obtener id
        return id; // 00191322 retornar id
    }

    public void setId(Integer id) { // 00191322 Metodo setear Id
        this.id = id; // 00191322 se setea Id
    }

    public Double getMonto() { // 00191322 Metodo obtener monto
        return monto; // 00191322 retornar monto
    }

    public void setMonto(Double monto) { // 00191322 Metodo setear monto
        this.monto = monto; // 00191322 se setea monto
    }

    public Integer getIdTarjeta() { // 00191322 Metodo obtener idTarjeta
        return idTarjeta; // 00191322 retornar idTarjeta
    }

    public void setIdTarjeta(Integer idTarjeta) { // 00191322 Metodo setear idTarjeta
        this.idTarjeta = idTarjeta; // 00191322 se setea idTarjeta
    }

    public String getDescripcion() { // 00191322 Metodo obtener descripcion
        return descripcion; // 00191322 retornar descripcion
    }

    public void setDescripcion(String descripcion) { // 00191322 Metodo setear descripcion
        this.descripcion = descripcion; // 00191322 se setea descripcion
    }

    public String getFechaDeCompra() { // 00191322 Metodo obtener fechaDeCompra
        return fechaDeCompra; // 00191322 retornar fechaDeCompra
    }

    public void setFechaDeCompra(String fechaDeCompra) { // 00191322 Metodo setear fechaDeCompra
        this.fechaDeCompra = fechaDeCompra; // 00191322 se setea fechaDeCompra
    }

    public String getClienteTarjeta() { // 00191322 Metodo obtener clienteTarjeta
        return clienteTarjeta; // 00191322 retornar clienteTarjeta
    }

    public void setClienteTarjeta(String clienteTarjeta) { // 00191322 Metodo setear clienteTarjeta
        this.clienteTarjeta = clienteTarjeta; // 00191322 se setea clienteTarjeta
    }

    @Override
    public String toString() { // 00191322 Metodo toString para Compra
        return "\nMonto: " + monto + ", Id Tarjeta: " + idTarjeta + "\n"; // 00191322 Se setea que se quiere devolver
    }
}