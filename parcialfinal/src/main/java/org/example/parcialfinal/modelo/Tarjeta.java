    package org.example.parcialfinal.modelo;
    
    public class Tarjeta {
        // 00084020 atributo clase publica Tarjeta
        private Integer id;
        //00084020 atributo privado para almacenar id tarjeta
        private String numero;
        //00084020 atributo privado para almacenar numero de tarjeta
        private String fechaVencimiento;
        //00084020 atributo privado para almacenar vencimiento tarjeta
        private String codigo;
        //00084020 atributo privado para almacenar el codigo de tarjeta
        private String tipoTarjeta;
        //00084020 atributo privado para almacenar tipo de tarjeta, debito o credito
        private Integer facilitadorId;
        // 00084020 atributo privado para almacenar el id del facilitador de tarjeta
        private String nombreFacilitador;
        //00084020 atributo privado para almacenar el nombre del facilidad
        private Integer clienteId;
        // 00084020 atributo privado para almacenar el id del cliente
        private String nombreCliente;
        //00084020 atributo privado para almacenar el nombre del cliente
    
        public Tarjeta() {
            //00084020 constructor vacio para inicializar
        }
    
        public Tarjeta(Integer id, String numero, String fechaVencimiento, String codigo, String tipoTarjeta, Integer facilitadorId, String nombreFacilitador, Integer clienteId, String nombreCliente) {
            //00084020 constructor con parametros para inicializar instancia
            this.id = id;
            //00084020 inicializa el atributo id con valores
            this.numero = numero;
            //00084020 inicializa el atributo numero con valores
            this.fechaVencimiento = fechaVencimiento;
            //00084020 inicializa el atributo fechaVencimiento con valores
            this.codigo = codigo;
            // 00084020 inicializa atributo codigo con valores
            this.tipoTarjeta = tipoTarjeta;
            // 00084020 inicializa atributo tipoTarjeta con valores
            this.facilitadorId = facilitadorId;
            //00084020 inicializa atributo facilitadorId con valores
            this.nombreFacilitador = nombreFacilitador;
            //00084020 inicializa atributo nombreFacilitador con valores
            this.clienteId = clienteId;
            //00084020 inicializa atributo clienteId con valores
            this.nombreCliente = nombreCliente;
            //00084020 inicializa atributo nombreCliente con valores
        }
    
        public Integer getId() {
            return id;
        }
        //00084020 getter para obtener atributo id
    
        public void setId(Integer id) {
            this.id = id;
        }
        //00084020 setter para obtener atributo Id
    
        public String getNumero() {
            return numero;
        }
        //00084020 getter para obtener atributo numero
    
        public void setNumero(String numero) {
            this.numero = numero;
        }
        //00084020 setter para obtener atributo numero
    
        public String getFechaVencimiento() {
            return fechaVencimiento;
        }
        //00084020 getter para obtener atributo fechaVencimiento

        public void setFechaVencimiento(String fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }
        //00084020 setter para obtener atributo fechaVencimiento


        public String getCodigo() {
            return codigo;
        }
        //00084020 getter para obtener atributo Codigo

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }
        //00084020 setter para obtener atributo codigo
    
        public Integer getFacilitadorId() {
            return facilitadorId;
        }
        //00084020 getter para obtener atributo facilitadorId
    
        public void setFacilitadorId(Integer facilitadorId) {
            this.facilitadorId = facilitadorId;
        }
        //00084020 setter para obtener atributo facilitadorId
    
        public String getTipoTarjeta() {
            return tipoTarjeta;
        }
        //00084020 getter para obtener atributo tipoTarjeta
    
        public void setTipoTarjeta(String tipoTarjeta) {
            this.tipoTarjeta = tipoTarjeta;
        }
        //00084020 setter para obtener atributo tipoTarjeta
    
        public String getNombreFacilitador() {
            return nombreFacilitador;
        }
        //00084020 getter para obtener atributo nombreFacilitador
    
        public void setNombreFacilitador(String nombreFacilitador) {
            this.nombreFacilitador = nombreFacilitador;
        }
        //00084020 setter para obtener atributo nombreFacilitador
    
        public Integer getClienteId() {
            return clienteId;
        }
        //00084020 getter para obtener atributo clienteId
    
        public void setClienteId(Integer clienteId) {
            this.clienteId = clienteId;
        }
        //00084020 setter para obtener atributo clienteId
    
        public String getNombreCliente() {
            return nombreCliente;
        }
        //00084020 getter para obtener atributo nombreCliente
    
        public void setNombreCliente(String nombreCliente) {
            this.nombreCliente = nombreCliente;
        }
        //00084020 getter para obtener atributo nombreCliente
    
        @Override
        public String toString() {
            return numero;
        }
        // //00084020 Sobreescribe el metodo ToString para retornar el numero tarjeta
    }
