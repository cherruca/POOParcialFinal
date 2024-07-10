package org.example.parcialfinal.modelo;

public class Compra {
    private Integer id;
    private Double monto;
    private Integer idTarjeta;
    private String descripcion;
    private String fechaDeCompra;
    private String clienteTarjeta;

    public Compra() {
    }

    public Compra(Integer id, Double monto, Integer idTarjeta, String descripcion, String fechaDeCompra) {
        this.id = id;
        this.monto = monto;
        this.idTarjeta = idTarjeta;
        this.descripcion = descripcion;
        this.fechaDeCompra = fechaDeCompra;
    }

    public Compra(Integer id, Double monto, Integer idTarjeta, String descripcion, String fechaDeCompra, String clienteTarjeta) {
        this.id = id;
        this.monto = monto;
        this.idTarjeta = idTarjeta;
        this.descripcion = descripcion;
        this.fechaDeCompra = fechaDeCompra;
        this.clienteTarjeta = clienteTarjeta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaDeCompra() {
        return fechaDeCompra;
    }

    public void setFechaDeCompra(String fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    public String getClienteTarjeta() {
        return clienteTarjeta;
    }

    public void setClienteTarjeta(String clienteTarjeta) {
        this.clienteTarjeta = clienteTarjeta;
    }

    @Override
    public String toString() {
        return "\nMonto: " + monto + ", Id Tarjeta: " + idTarjeta + "\n";
    }
}


//txtCompraId.clear();
//            txtMonto.clear();
//            txtAreaDescripcion.clear();
//            datePickerFechaCompra.setValue(null);
//            cbTarjetas.setConverter(null);
//            cbTarjetas.valueProperty().set(null);