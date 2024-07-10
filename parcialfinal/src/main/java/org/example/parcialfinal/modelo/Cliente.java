package org.example.parcialfinal.modelo;

public class Cliente { // 00191322 Clase Cliente
    private Integer id; // 00191322 Atributo id
    private String nombre; // 00191322 Atributo nombre
    private String telefono; // 00191322 Atributo telefono
    private String direccion; // 00191322 Atributo direccion

    public Cliente() { // 00191322 Constructor vacio
    }

    public Cliente(Integer id, String nombre, String telefono, String direccion) { // 00191322 Constructor con paremetros
        this.id = id; // 00191322 setear id con controlador
        this.nombre = nombre; // 00191322 setear nombre con controlador
        this.telefono = telefono; // 00191322 setear telefono con controlador
        this.direccion = direccion; // 00191322 setear direccion con controlador
    }

    public Integer getId() { // 00191322 Metodo obtener id
        return id; // 00191322 retornar id
    }

    public void setId(Integer id) { // 00191322 Metodo setear Id
        this.id = id; // 00191322 se setea Id
    }

    public String getNombre() { // 00191322 Metodo obtener nombre
        return nombre; // 00191322 retornar nombre
    }

    public void setNombre(String nombre) {  // 00191322 Metodo setear nombre
        this.nombre = nombre; // 00191322 se setea nombre
    }

    public String getTelefono() { // 00191322 Metodo obtener telefono
        return telefono; // 00191322 retornar telefono
    }

    public void setTelefono(String telefono) {  // 00191322 Metodo setear telefono
        this.telefono = telefono; // 00191322 se setea telefono
    }

    public String getDireccion() { // 00191322 Metodo obtener direccion
        return direccion; // 00191322 retornar direccion
    }

    public void setDireccion(String direccion) {  // 00191322 Metodo setear direccion
        this.direccion = direccion; // 00191322 se setea direccion
    }

    @Override
    public String toString() { // 00191322 Metodo toString para Cliente
        return nombre + " " + telefono + " " + direccion; // 00191322 Se setea que se quiere devolver
    }
}
