package org.example.parcialfinal.modelo;

public class Tarjeta {
    private Integer id;
    private String numero;
    private String fechaVencimiento;
    private String codigo;
    private String tipoTarjeta;
    private Integer facilitadorId;
    private String nombreFacilitador;
    private Integer clienteId;
    private String nombreCliente;

    public Tarjeta() {
    }

    public Tarjeta(Integer id, String numero, String fechaVencimiento, String codigo, String tipoTarjeta, Integer facilitadorId, String nombreFacilitador, Integer clienteId, String nombreCliente) {
        this.id = id;
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.codigo = codigo;
        this.tipoTarjeta = tipoTarjeta;
        this.facilitadorId = facilitadorId;
        this.nombreFacilitador = nombreFacilitador;
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getFacilitadorId() {
        return facilitadorId;
    }

    public void setFacilitadorId(Integer facilitadorId) {
        this.facilitadorId = facilitadorId;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNombreFacilitador() {
        return nombreFacilitador;
    }

    public void setNombreFacilitador(String nombreFacilitador) {
        this.nombreFacilitador = nombreFacilitador;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String toString() {
        return numero;
    }
}
