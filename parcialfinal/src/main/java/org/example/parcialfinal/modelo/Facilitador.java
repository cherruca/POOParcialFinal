package org.example.parcialfinal.modelo;

public class Facilitador { // 00051316 Declara la clase Facilitador
    private int id; // 00051316 Declara el atributo privado id de tipo int
    private String tipo; // 00051316 Declara el atributo privado tipo de tipo String

    public Facilitador() { // 00051316 Constructor por defecto de la clase Facilitador

    }

    public Facilitador(int id, String tipo) { // 00051316 Constructor con parametros de la clase Facilitador
        this.id = id; // 00051316 Inicializa el atributo id con el valor pasado como parametro
        this.tipo = tipo; // 00051316 Inicializa el atributo tipo con el valor pasado como parametr
    }

    public int getId() { // 00051316 Metodo getter para obtener el valor del atributo id
        return id; // 00051316 Devuelve el valor del atributo id
    }

    public void setId(int id) {  // 00051316 Metodo setter para obtener el valor del atributo id
        this.id = id; // 00051316  Asigna el valor pasado como parametro al atributo id
    }

    public String getTipo() { // 00051316 Metodo getter para obtener el valor del atributo tipo
        return tipo; // 00051316 Devuelve el valor del atributo tipo
    }

    public void setTipo(String tipo) { // 00051316 Metodo setter para obtener el valor del atributo tipo
        this.tipo = tipo; // 00051316 Asigna el valor pasado como parametro al atributo tipo
    }

    @Override
    public String toString() { // 00191322 Metodo toString para Facilitador
        return id + " " + tipo; // 00191322 Se setea que se quiere devolver
    }
}
