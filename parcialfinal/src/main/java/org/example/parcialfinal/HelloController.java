package org.example.parcialfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import org.example.parcialfinal.controllador.*;
import org.example.parcialfinal.modelo.Cliente;
import org.example.parcialfinal.modelo.Compra;
import org.example.parcialfinal.modelo.Facilitador;

import org.example.parcialfinal.modelo.ReporteA;
import org.example.parcialfinal.modelo.ReporteParametro;

import org.example.parcialfinal.modelo.Tarjeta;


import java.net.URL;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button btnBuscarCliente; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es buscar un cliente

    @FXML
    private Button btnBuscarCompra; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es buscar una compra

    @FXML
    private Button btnBuscarTarjeta; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es buscar una tarjeta

    @FXML
    private Button btnEliminarCliente; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es eliminar un cliente

    @FXML
    private Button btnEliminarCompra; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es eliminar una compra

    @FXML
    private Button btnEliminarTarjeta; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es eliminar una tarjeta

    @FXML
    private Button btnGuardarCliente; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es guardar un cliente

    @FXML
    private Button btnGuardarCompra; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es guardar una compra

    @FXML
    private Button btnGuardarTarjeta; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es guardar una tarjeta

    @FXML
    private ComboBox<Cliente> cbClientes; // 00191322 Combobox que de tipo Cliente

    @FXML
    private ComboBox<Facilitador> cbFacilitador; // 00191322 Combobox que de tipo Facilitador

    @FXML
    private ComboBox<Tarjeta> cbTarjetas; // 00191322 Combobox que de tipo Tarjeta

    @FXML
    private ComboBox<String> cbTipoTarjeta; // 00191322 Combobox que de tipo String (Tipo Tarjeta, ya sea Debito o Credito)

    @FXML
    private DatePicker datePickerFechaCompra; // 00191322 Datepicker para elegir la fecha de la compra

    @FXML
    private TableView<Cliente> tblClientes; // 00191322 TableView que mostrara los clientes traidos desde la base

    @FXML
    private TableView<Compra> tblCompras; // 00191322 TableView que mostrara las compras traidas desde la base

    @FXML
    private TableView<Tarjeta> tblTarjetas; // 00191322 TableView que mostrara las tarjetas traidas desde la base

    @FXML
    private TableColumn<Cliente, String> tcClienteDireccion; // 00191322 Columna de la Tabla de Cliente que representara la direccion del Cliente

    @FXML
    private TableColumn<Cliente, Integer> tcClienteId; // 00191322 Columna de la Tabla de Cliente que representara el Id del Cliente

    @FXML
    private TableColumn<Cliente, String> tcClienteNombre; // 00191322 Columna de la Tabla de Cliente que representara el Nombre del Cliente

    @FXML
    private TableColumn<Cliente, String> tcClienteTelefono; // 00191322 Columna de la Tabla de Cliente que representara el Telefono del Cliente

    @FXML
    private TableColumn<Compra, String> tcCompraDescripcion; // 00191322 Columna de la Tabla de Compra que representara la Descripcion de la Compra

    @FXML
    private TableColumn<Compra, String> tcCompraFechaCompra; // 00191322 Columna de la Tabla de Compra que representara la Fecha de la Compra

    @FXML
    private TableColumn<Compra, Integer> tcCompraId; // 00191322 Columna de la Tabla de Compra que representara el Id de la Compra

    @FXML
    private TableColumn<Compra, Double> tcCompraMonto; // 00191322 Columna de la Tabla de Compra que representara el Monto de la Compra

    @FXML
    private TableColumn<Compra, Integer> tcCompraTarjeta; // 00191322 Columna de la Tabla de Compra que representara la Tarjeta usada en la Compra

    @FXML
    private TableColumn<Tarjeta, String> tcTarjetaCvc; // 00191322 Columna de la Tabla de Tarjeta que representara el codigo CVC de la Tarjeta

    @FXML
    private TableColumn<Tarjeta, String> tcTarjetaFechaVen; // 00191322 Columna de la Tabla de Tarjeta que representara la Fecha de Vencimiento de la Tarjeta

    @FXML
    private TableColumn<Tarjeta, Integer> tcTarjetaId; // 00191322 Columna de la Tabla de Tarjeta que representara el Id de la Tarjeta

    @FXML
    private TableColumn<Tarjeta, Integer> tcTarjetaNomCliente; // 00191322 Columna de la Tabla de Tarjeta que representara el Nombre del Cliente asociado a la Tarjeta

    @FXML
    private TableColumn<Tarjeta, String> tcTarjetaNum; // 00191322 Columna de la Tabla de Tarjeta que representara el Numero de la Tarjeta

    @FXML
    private TableColumn<Tarjeta, String> tcTarjetaTipo; // 00191322 Columna de la Tabla de Tarjeta que representara el Tipo de la Tarjeta

    @FXML
    private TableColumn<Tarjeta, String> tcTarjetaFacilitador; // 00191322 Columna de la Tabla de Tarjeta que representara el Facilitador (Visa, Mastercard, etc) de la Tarjeta

    @FXML
    private TextArea txtAreaDescripcion; // 00191322 Text Area para Compra donde se introduce la descripcion

    @FXML
    private TextArea txtAreaDireccion; // 00191322 Text Area para Cliente donde se introduce la direccion

    @FXML
    private TextField txtClienteId; // 00191322 Text Field para Cliente que contiene el Id (esta oculto en la vista)

    @FXML
    private TextField txtTarjetaId; // 00191322 Text Field para Tarjeta que contiene el Id (esta oculto en la vista)

    @FXML
    private TextField txtCompraId; // 00191322 Text Field para Compra que contiene el Id (esta oculto en la vista)

    @FXML
    private TextField txtBuscarCliente; // 00191322 Text Field para Buscar un Cliente

    @FXML
    private TextField txtBuscarCompra; // 00191322 Text Field para Buscar una Compra

    @FXML
    private TextField txtBuscarTarjeta; // 00191322 Text Field para Buscar una Tarjeta

    @FXML
    private TextField txtClienteNombre; // 00191322 Text Field para Cliente donde se introduce el Nombre

    @FXML
    private TextField txtClienteTelefono; // 00191322 Text Field para Cliente donde se introduce el Telefono

    @FXML
    private TextField txtCvc; // 00191322 Text Field para Tarjeta donde se introduce el CVC

    @FXML
    private TextField txtFechaVencimiento; // 00191322 Text Field para Tarjeta donde se introduce la fecha de vencimiento

    @FXML
    private TextField txtMonto; // 00191322 Text Field para Compra donde se introduce el Monto

    @FXML
    private TextField txtNumTarjeta; // 00191322 Text Field para Tarjeta donde se introduce el numero de Tarjeta

    @FXML
    private VBox vbMenu; // 00191322 Elemento VBox para el menu

    // reportes
    @FXML
    private Button btnReporteA;
    @FXML
    private Button btnReporteB;
    @FXML
    private Button btnReporteC;
    @FXML
    private Button btnReporteD;
    @FXML
    private ComboBox<?> cbReporteBanio;
    @FXML
    private ComboBox<?> cbReporteBmes;
    @FXML
    private ComboBox<?> cbReporteDfacilitador;
    @FXML
    private DatePicker reporteAFechaFin;
    @FXML
    private DatePicker reporteAFechaInicio;
    @FXML
    private ComboBox<Cliente> reporteCliente;
    @FXML
    private TextArea webReporte;

    private ArrayList<Cliente> listadoClientesCombo;

    ObservableList<Cliente> observableListCliente = FXCollections.observableArrayList();
    ObservableList<Facilitador> observableListFacilitador = FXCollections.observableArrayList();
    ObservableList<Tarjeta> observableListTarjeta = FXCollections.observableArrayList();
    ObservableList<Compra> observableListCompra = FXCollections.observableArrayList();

    ObservableList<String> observableListTipoTarjeta = FXCollections.observableArrayList();

    /**
     * Se declaran los controladores como atributos y se
     * hace una simulacion de Dependency Injection (Inyeccion de Dependencias)
     */
    private ClienteControlador clienteControlador; // Atributo privado de tipo Controlador de Cliente
    private CompraControlador compraControlador; // Atributo privado de tipo Controlador de Compra
    private TarjetaControlador tarjetaControlador; // Atributo privado de tipo Controlador de Tarjeta
    private FacilitadorControlador facilitadorControlador; // Atributo privado de tipo Controlador de Facilitador


    // Constructor sin argumentos requerido por FXM Loader
    public HelloController() {
        this(new ClienteControlador(), new CompraControlador(), new TarjetaControlador(), new FacilitadorControlador()); // Se llama el controlador con argumentos de los controladores para siempre incializar una instancia con los atributos seteados
    }

    /**
     * Se inicializan las instancias de los controladores
     * por medio de un constructor de la clase
     * @param clienteControlador instancia de ClienteControlador a ser inyectada
     */
    public HelloController(ClienteControlador clienteControlador, CompraControlador compraControlador, TarjetaControlador tarjetaControlador, FacilitadorControlador facilitadorControlador) {
        this.clienteControlador = clienteControlador;
        this.compraControlador = compraControlador;
        this.tarjetaControlador = tarjetaControlador;
        this.facilitadorControlador = facilitadorControlador;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        obtenerTarjetas();
        obtenerClientes();
        obtenerFacilitadores();
        obtenerCompras();
        /**
         * Se obtiene el header de la tabla usando un listener,
         * luego se agrega otro listener a la propiedad de reordering (reordenamiento)
         * y se asigan false al atributo Reordering
         *
         * Con esto, se logra que las columnas de las tablas no se pueden ajustar su tamaño
         */
        tblClientes.widthProperty().addListener((observableValue, number, t1) -> {
            TableHeaderRow header = (TableHeaderRow) tblClientes.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((observableValue1, aBoolean, t11) -> header.setReordering(false));
        });

        tblTarjetas.widthProperty().addListener((observableValue, number, t1) -> {
            TableHeaderRow header = (TableHeaderRow) tblTarjetas.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((observableValue1, aBoolean, t11) -> header.setReordering(false));
        });

        tblCompras.widthProperty().addListener((observableValue, number, t1) -> {
            TableHeaderRow header = (TableHeaderRow) tblCompras.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((observableValue1, aBoolean, t11) -> header.setReordering(false));
        });

        /**
         * Se asigna un value factory a las columnas para
         * definir que atributo van a mostrar
         */
        tcClienteId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcClienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcClienteTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tcClienteDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        tcTarjetaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTarjetaNomCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        tcTarjetaNum.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tcTarjetaCvc.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcTarjetaFechaVen.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
        tcTarjetaFacilitador.setCellValueFactory(new PropertyValueFactory<>("nombreFacilitador"));
        tcTarjetaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoTarjeta"));

        tcCompraId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCompraMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tcCompraTarjeta.setCellValueFactory(new PropertyValueFactory<>("clienteTarjeta"));
        tcCompraDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcCompraFechaCompra.setCellValueFactory(new PropertyValueFactory<>("fechaDeCompra"));

        /**
         * Se asigna datos a la tabla por medio de un ObservableList
         */
        tblClientes.setItems(observableListCliente);
        tblTarjetas.setItems(observableListTarjeta);
        tblCompras.setItems(observableListCompra);

        tblClientes.setRowFactory(object -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    Cliente cliente = row.getItem();
                    if(row.getIndex() == tblClientes.getSelectionModel().getSelectedIndex()
                            && Objects.equals(txtClienteId.getText(), String.valueOf(cliente.getId()))
                    ){
                        tblClientes.getSelectionModel().clearSelection();
                        limpiarCampos("cliente");
                    } else {
                        txtClienteId.setText(cliente.getId().toString());
                        txtClienteNombre.setText(cliente.getNombre());
                        txtClienteTelefono.setText(cliente.getTelefono());
                        txtAreaDireccion.setText(cliente.getDireccion());
                    }
                }
            });
            return row;
        });

        tblTarjetas.setRowFactory(object -> {
            TableRow<Tarjeta> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    Tarjeta tarjeta = row.getItem();
                    if(row.getIndex() == tblTarjetas.getSelectionModel().getSelectedIndex()
                            && Objects.equals(txtTarjetaId.getText(), String.valueOf(tarjeta.getId()))
                    ){
                        tblTarjetas.getSelectionModel().clearSelection();
                        limpiarCampos("tarjeta");
                    } else {
                        txtTarjetaId.setText(tarjeta.getId().toString());
                        txtNumTarjeta.setText(tarjeta.getNumero());
                        txtFechaVencimiento.setText(tarjeta.getFechaVencimiento());
                        txtCvc.setText(tarjeta.getCodigo());
                        cbTipoTarjeta.setValue(tarjeta.getTipoTarjeta());
                        cbClientes.setValue(clienteControlador.buscarCliente(tarjeta.getClienteId()));
                        cbFacilitador.setValue(facilitadorControlador.obtenerFacilitador(tarjeta.getFacilitadorId()));
                    }
                }
            });
            return row;
        });

        tblCompras.setRowFactory(object -> {
            TableRow<Compra> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    Compra compra = row.getItem();
                    if(row.getIndex() == tblCompras.getSelectionModel().getSelectedIndex()
                            && Objects.equals(txtCompraId.getText(), String.valueOf(compra.getId()))
                    ){
                        tblCompras.getSelectionModel().clearSelection();
                        limpiarCampos("compra");
                    } else {
                        txtCompraId.setText(compra.getId().toString());
                        txtMonto.setText(compra.getMonto().toString());
                        datePickerFechaCompra.setValue(stringALocalDate(compra.getFechaDeCompra()));
                        txtAreaDescripcion.setText(compra.getDescripcion());
                        cbTarjetas.setValue(tarjetaControlador.obtenerTarjeta(compra.getIdTarjeta()));
                    }
                }
            });
            return row;
        });

        cbFacilitador.setItems(observableListFacilitador);
        cbClientes.setItems(observableListCliente);
        cbTarjetas.setItems(observableListTarjeta);

        List<String> tipoTarjeta = new ArrayList<>();
        tipoTarjeta.add("Debito");
        tipoTarjeta.add("Credito");

        observableListTipoTarjeta.addAll(tipoTarjeta);

        cbTipoTarjeta.setItems(observableListTipoTarjeta);
    }

    @FXML
    public void vistaClientes() {
        limpiarCampos("cliente");
        obtenerClientes();

        cargarComboClientes(); // 00402523 rellena el combo de clientes para generar los reportes

    }

    @FXML
    public void vistaCompras(){
        limpiarCampos("compra");
        comboboxVistaCompras();
    }

    public void comboboxVistaCompras() {
        obtenerCompras();
        cbTarjetas.setConverter(new StringConverter<Tarjeta>() {
            @Override
            public String toString(Tarjeta object) {
                return object.getNumero();
            }

            @Override
            public Tarjeta fromString(String string) {
                return cbTarjetas.getItems().stream().filter(tarjeta ->
                        tarjeta.getNumero().equals(string)).findFirst().orElse(null);
            }
        });
    }

    @FXML
    public void vistaTarjetas(){
        limpiarCampos("tarjeta");
        comboboxVistaTarjetas();
    }

    public void comboboxVistaTarjetas() {
        obtenerTarjetas();
        obtenerClientes();
        obtenerFacilitadores();

        cbFacilitador.setConverter(new StringConverter<Facilitador>() {

            @Override
            public String toString(Facilitador object) {
                return object.getTipo();
            }

            @Override
            public Facilitador fromString(String string) {
                return cbFacilitador.getItems().stream().filter(facilitador ->
                        facilitador.getTipo().equals(string)).findFirst().orElse(null);
            }
        });

        cbClientes.setConverter(new StringConverter<Cliente>() {

            @Override
            public String toString(Cliente object) {
                return object.getNombre();
            }

            @Override
            public Cliente fromString(String string) {
                return cbClientes.getItems().stream().filter(cliente ->
                        cliente.getNombre().equals(string)).findFirst().orElse(null);
            }
        });
    }

    @FXML
    public List<Cliente> obtenerClientes() {
        // Se obtiene los registros de los clientes de la base de datos
        List<Cliente> clientes = clienteControlador.obtenerClientes();
        // Se limpia el ObservableList
        observableListCliente.clear();
        // Se añade la lista al ObservableList
        observableListCliente.addAll(clientes);
        return clientes;
    }

    @FXML
    public List<Facilitador> obtenerFacilitadores() {
        List<Facilitador> facilitadores = facilitadorControlador.obtenerFacilitadores();
        observableListFacilitador.clear();
        observableListFacilitador.addAll(facilitadores);
        return facilitadores;
    }

    @FXML
    public List<Compra> obtenerCompras(){
        List<Compra> compras = compraControlador.obtenerCompras();
        observableListCompra.clear();
        observableListCompra.addAll(compras);
        return compras;
    }

    @FXML
    public List<Tarjeta> obtenerTarjetas() {
        List<Tarjeta> tarjetas = tarjetaControlador.obtenerTarjetas();
        observableListTarjeta.clear();
        observableListTarjeta.addAll(tarjetas);
        return tarjetas;
    }

    @FXML
    protected void buscarCliente() {
        String termino = txtBuscarCliente.getText();
        if(verificarCamposConDatos("cliente", "buscar")) {
            List<Cliente> clientes = clienteControlador.buscarClientes(termino);
            observableListCliente.clear();
            observableListCliente.addAll(clientes);
        }
    }

    @FXML
    protected void buscarTarjeta() {
        String termino = txtBuscarTarjeta.getText();
        if(verificarCamposConDatos("tarjeta", "buscar")) {
            List<Tarjeta> tarjetas = tarjetaControlador.buscarTarjetas(termino);
            observableListTarjeta.clear();
            observableListTarjeta.addAll(tarjetas);
        }
    }

    @FXML
    protected void buscarCompra() {
        String termino = txtBuscarCompra.getText();
        if(verificarCamposConDatos("compra", "buscar")) {
            List<Compra> compras = compraControlador.buscarCompras(termino);
            observableListCompra.clear();
            observableListCompra.addAll(compras);
        }
    }

    @FXML
    protected void agregarCliente() { // 00051316 metodo publico para agregar una compra
        String accion; // 00051316 cariable para almacenar la accion realizada
        if(verificarCamposConDatos("cliente", "guardar")) { // 00051316 verifica si los campos requeridos para guardar un clinete estan completos
            Cliente cliente = new Cliente(); // 00051316 crea una nueva instancia de la clase cliente

            if (Objects.equals(txtClienteId.getText(), "")) { // 00051316 verifica si el campo de ID de clienteestá vacío
                cliente.setId(null); // 00051316 establece el ID de la cliente como nulo
                accion = "guardado"; // 00051316 Define la acción como guardado
            } else { // 00051316 de no cumplirse con los compos
                cliente.setId(Integer.valueOf(txtClienteId.getText())); // 00051316 Convierte y establece el iv de cliente desde el campo de texto
                accion = "actualizado"; // 00051316 Define la acción como actualizado
            }

            cliente.setNombre(txtClienteNombre.getText()); // 00051316 establece los atributos del cliente desde los campos del formulario
            cliente.setTelefono(txtClienteTelefono.getText()); // 00051316 establece los atributos del cliente desde los campos del formulario
            cliente.setDireccion(txtAreaDireccion.getText()); // 00051316 establece los atributos del cliente desde los campos del formulario

            if(clienteControlador.persistirCliente(cliente)) { // 00051316 intenta persistir el cliente usando el controlador de cliente
                lanzarAlerta("Exito", "Cliente " + accion, Alert.AlertType.INFORMATION); // 00051316 Muestra una alerta de exito
                limpiarCampos("cliente"); // 00051316 llama al método para limpiar los campos
                obtenerClientes(); // 00051316 llama al metodo para actualizar la vista de clientes
            } else { // 00051316 de no cumplirse con los compos
                lanzarAlerta("Cliente no guardado", "Hubo un error al guardar el registro", Alert.AlertType.ERROR); // 00051316 muestra una alerta de error
            }
        }
    }

    @FXML
    protected void eliminarCliente() { // 00051316 metodo publico para eliminar un cliente
        if(verificarCamposConDatos("cliente", "eliminar")){ // 00051316 verifica si los campos requeridos para eliminar un cliente están completos
            // 00051316 intenta eliminar el cliente usando el controlador de tarjeta
            if(clienteControlador.eliminarCliente(Integer.parseInt(txtClienteId.getText()))) { // 00051316 intenta convertir el id de cliente a entero y luego eliminarlo
                lanzarAlerta("Exito", "Cliente eliminado", Alert.AlertType.INFORMATION); // 00051316 muestra una alerta de exito
                limpiarCampos("cliente"); // 00051316 llama al metodo para limpiar los campos
                obtenerClientes(); // 00051316 llama al metodo para obtener y actualizar la lista de tarjeta
            } else { // 00051316 salta si alguno de los datos anteriores no esta completado
                lanzarAlerta("Cliente no eliminado", "Hubo un error al eliminar el registro", Alert.AlertType.ERROR); // 00051316 muestra una alerta de error
            }
        }
    }

    @FXML
    public void agregarTarjeta() { // 00051316 metodo publico para agregar una compra
        String accion; // 00051316 variable para almacenar la acción realizada

        if(verificarCamposConDatos("tarjeta", "guardar")){// 00051316 verifica si los campos requeridos para guardar una tarjeta están completos
            Tarjeta tarjeta = new Tarjeta();// 00051316 Crea una nueva instancia de la clase Tarjeta
            if (Objects.equals(txtTarjetaId.getText(), "")) {// 00051316 verifica si el campo de id de compra está vacío
                tarjeta.setId(null);// 00051316 establece el id de la compra como nulo
                accion = "guardada";// 00051316 define la accion como guardada
            } else {// 00051316 de lo contrario
                tarjeta.setId(Integer.valueOf(txtTarjetaId.getText()));// 00051316 convierte y establece el ID de compra desde el campo de texto
                accion = "actualizada";// 00051316 define la accion como  actualizada
            }

            tarjeta.setNumero(txtNumTarjeta.getText());// 00051316 establece los atributos de la tarjeta desde los campos del formulario
            tarjeta.setFechaVencimiento(txtFechaVencimiento.getText());// 00051316 establece los atributos de la tarjeta desde los campos del formulario
            tarjeta.setCodigo(txtCvc.getText());// 00051316 establece los atributos de la tarjeta desde los campos del formulario
            tarjeta.setTipoTarjeta(cbTipoTarjeta.getValue());// 00051316 establece los atributos de la tarjeta desde los campos del formulario
            tarjeta.setFacilitadorId(cbFacilitador.getSelectionModel().getSelectedItem().getId());// 00051316 establece los atributos de la tarjeta desde los campos del formulario
            tarjeta.setClienteId(cbClientes.getSelectionModel().getSelectedItem().getId());// 00051316 establece los atributos de la tarjeta desde los campos del formulario

            if(tarjetaControlador.persistirTarjeta(tarjeta)) {// 00051316 intenta persistir la tarejta usando el controlador de tarjeta
                lanzarAlerta("Exito", "Tarjeta " + accion, Alert.AlertType.INFORMATION);// 00051316 muestra una alerta de exito
                limpiarCampos("tarjeta");// 00051316 llama al metodo para limpiar los campos
                obtenerTarjetas();// 00051316 llama al metodo para obtener y actualizar la lista de tarejtas
            } else {// 00051316 salta si alguno de los datos anteriores no esta completado
                lanzarAlerta("Tarjeta no guardada", "Hubo un error al guardar el registro", Alert.AlertType.ERROR);// 00051316 muestra una alerta de error
            }
        }
    }

    @FXML
    protected void eliminarTarjeta() { // 00051316 metodo para eliminar una tarjeta
        if(verificarCamposConDatos("tarjeta", "eliminar")){ // 00051316 verifica si los campos requeridos para eliminar una tarjeta están completos
            // 00051316 intenta eliminar la compra usando el controlador de tarjeta
            if(tarjetaControlador.eliminarTarjeta(Integer.parseInt(txtTarjetaId.getText()))) { // 00051316 Intenta convertir el ID de tarjeta a entero y luego eliminarla
                lanzarAlerta("Exito", "Tarjeta eliminada", Alert.AlertType.INFORMATION);// 00051316 alerta de éxito
                limpiarCampos("tarjeta"); // 00051316 llama al metodo para limpiar los campos
                obtenerTarjetas(); // 00051316 llama al metodo para obtener y actualizar la lista de tarjetas
            } else { // 00051316 salta si alguno de los datos anteriores no esta completado
                lanzarAlerta("Tarjeta no eliminada", "Hubo un error al eliminar el registro", Alert.AlertType.ERROR); // 00051316 muestra una alerta de error
            }
        }
    }

    @FXML
    public void agregarCompra() { // 00051316  metodo para agregar una compra
        String accion; // 00051316  variable para almacenar la accion realizada (guardada o actualizada)
        if(verificarCamposConDatos("compra", "guardar")){ // 00051316  verifica si los campos requeridos para guardar una compra estan completados
            Compra compra = new Compra(); // 00051316 se crea una nueva instancia de la clase Compra
            if (Objects.equals(txtCompraId.getText(), "")) { // 00051316 verifica si el campo de id de compra esta vacío
                compra.setId(null); // 00051316 establece el id de la compra como nulo
                accion = "guardada"; // 00051316 define la acción como guardada
            } else { // 00051316 de no cumplirse con los compos
                compra.setId(Integer.valueOf(txtCompraId.getText())); // 00051316 convierte y establece el id de compra desde el campo de texto
                accion = "actualizada"; // 00051316 define la acción como actualizada
            }

            compra.setMonto(Double.valueOf(txtMonto.getText())); // 00051316 establece los atributos de la compra desde los campos del formulario
            compra.setDescripcion(txtAreaDescripcion.getText()); // 00051316 establece los atributos de la compra desde los campos del formulario
            compra.setFechaDeCompra(String.valueOf(datePickerFechaCompra.getValue())); // 00051316 establece los atributos de la compra desde los campos del formulario
            compra.setIdTarjeta(cbTarjetas.getSelectionModel().getSelectedItem().getId()); // 00051316  etablece los atributos de la compra desde los campos del formulario

            if(compraControlador.persistirCompra(compra)) { // 00051316 intenta persistir la compra usando el controlador de compras
                lanzarAlerta("Exito", "Compra " + accion, Alert.AlertType.INFORMATION); // 00051316 muestra una alerta de exito
                limpiarCampos("compra"); // 00051316 llama al metodo para limpiar los campos
                comboboxVistaCompras(); // 00051316 llama al metodo para actualizar la vista de compras en el combobox
                obtenerCompras(); // 00051316 llama al metodo para obtener y actualizar la lista de compras
            } else { // 00051316 de no cumplirse con los compos
                lanzarAlerta("Compra no guardada", "Hubo un error al guardar el registro", Alert.AlertType.ERROR); // 00051316 muestra una alerta de error
            }
        }
    }

    @FXML
    protected void eliminarCompra() { //00191322 metodo para eliminar una compra

        if(verificarCamposConDatos("compra", "eliminar")){ //00191322 verifica si los campos requeridos para eliminar una compra están completos
            // 000191322 intenta eliminar la compra usando el controlador de compras
            if(compraControlador.eliminarCompra(Integer.parseInt(txtCompraId.getText()))) { //00191322 Intenta convertir el ID de compra a entero y luego eliminarla
                lanzarAlerta("Exito", "Compra eliminada", Alert.AlertType.INFORMATION); //00191322  alerta de éxito
                limpiarCampos("compra"); //00191322 llama al metodo para limpiar los campos
                obtenerCompras(); //00191322 Llama al metodo para obtener y actualizar la lista de compras
            } else { //00191322 salta si alguno de los datos anteriores no esta completado
                lanzarAlerta("Compra no eliminada", "Hubo un error al eliminar el registro", Alert.AlertType.ERROR); //00191322 llama al metodo para mostrar una alerta de error
            }
        }
    }

    /**
     * Metodo que nos permitira lanzar alertas al usuario
     *
     * @param mensaje mensaje a mostrar en nuestra alerta
     * @param tipo tipo de alerta en base al enum estatico AlertType de la clase Alert
     *             cuyos valores pueden ser: NONE, INFORMATION, WARNING, CONFIRMATION, y ERROR
     */
    private void lanzarAlerta(String mensaje, Alert.AlertType tipo) {
        // Definimos el tipo de alerta
        Alert alert = new Alert(tipo);
        // Titulo de nuestra ventana
        alert.setTitle("Mensaje del sistema");
        // Asignamos el valor del header, en este caso null
        alert.setHeaderText(null);
        // El mensaje de contendra nuestra ventana
        alert.setContentText(mensaje);
        // Metodo que bloquea las demas ventanas y espera hasta que el usario interactue con el cuadro de dialogo
        alert.showAndWait();
    }

    /**
     * Se hace un overloading de lanzarAlerta() teniendo
     * un metodo con 3 parametros y otro con 2 parametros
     * @param titulo
     * @param mensaje
     * @param tipo
     */
    private void lanzarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        // Definimos el tipo de alerta
        Alert alert = new Alert(tipo);
        // Titulo de nuestra ventana
        alert.setTitle(titulo);
        // Asignamos el valor del header, en este caso null
        alert.setHeaderText(null);
        // El mensaje de contendra nuestra ventana
        alert.setContentText(mensaje);
        // Metodo que bloquea las demas ventanas y espera hasta que el usario interactue con el cuadro de dialogo
        alert.showAndWait();
    }

    @FXML
    private void limpiarCampos(String modelo) { //00191322 declaramo el metodo limpiarCampos que recibe un parámetro modelo de tipo String
        if(Objects.equals(modelo, "cliente")) { //00191322 verificar si el modelo es cliente
            txtClienteId.clear(); //00191322 limpiamos el campo txtClienteId
            txtClienteNombre.clear(); //00191322 limpia el campo txtClienteNombre
            txtClienteTelefono.clear(); //00191322 limpia el campo txtClienteTelefono
            txtAreaDireccion.clear(); //00191322 limpia el campo txtAreaDireccion
        } else if (Objects.equals(modelo, "tarjeta")) { //00191322 verifica si el modelo es "tarjeta"
            txtTarjetaId.clear(); //00191322 limpia el campo txtTarjetaId
            txtNumTarjeta.clear(); //00191322 limpia el campo txtNumTarjeta
            txtFechaVencimiento.clear(); //00191322 limpia el campo txtFechaVencimiento
            txtCvc.clear(); //00191322 limpia el campo txtCvc
            cbClientes.setConverter(null); //00191322 resetear el convertidor del combobox cbClientes
            cbTipoTarjeta.setConverter(null); //00191322 resetear el convertidor del combobox cbTipoTarjeta
            cbFacilitador.setConverter(null); //00191322 resetear el convertidor del combobox cbFacilitador
            cbClientes.valueProperty().set(null); //00191322 limpiar la selección en cbClientes
            cbTipoTarjeta.valueProperty().set(null); //00191322 limpiar la selección en cbTipoTarjeta
            cbFacilitador.valueProperty().set(null); //00191322 limpiar la selección en cbFacilitador
            comboboxVistaTarjetas(); //00191322 llamar al metodo comboboxVistaTarjetas
        } else if (Objects.equals(modelo, "tarjeta-campos")) { //00191322 verificar si el modelo es "tarjeta-campos"
            txtTarjetaId.clear(); //00191322 limpiar el campo txtTarjetaId
            txtNumTarjeta.clear(); //00191322 limpia rel campo txtNumTarjeta
            txtFechaVencimiento.clear(); //00191322 limpiarel campo txtFechaVencimiento
            txtCvc.clear(); //00191322 limpiar el campo txtCvc
            cbClientes.valueProperty().set(null); //00191322 limpiar la selección en cbClientes
            cbTipoTarjeta.valueProperty().set(null); //00191322 limpiar la selección en cbTipoTarjeta
            cbFacilitador.valueProperty().set(null); //00191322 limpiar la selección en cbFacilitador
        } else if (Objects.equals(modelo, "compra")) { //00191322  verificar si el modelo es "compra"
            txtCompraId.clear(); //00191322 limpiar el campo txtCompraId
            txtMonto.clear(); //00191322 limpiar el campo txtMonto
            txtAreaDescripcion.clear(); //00191322 limpiar el campo txtAreaDescripcion
            datePickerFechaCompra.setValue(null); //00191322 limpiar la fecha en datePickerFechaCompra
            cbTarjetas.setConverter(null); //00191322 resetear el convertidor del combobox cbTarjetas
            cbTarjetas.valueProperty().set(null); //00191322 limpiar la selección en cbTarjetas
            comboboxVistaCompras(); //00191322 llamar al metodo comboboxVistaCompras
        }
    }

    private boolean verificarCamposConDatos(String modelo, String accion){ //00191322 Declaramo el metodo verificarCamposConDatos que recibe parametros modelo de tipo String y accion tipo string
        if(Objects.equals(modelo, "cliente")) { //00191322 verificar si el modelo es cliente
            if(Objects.equals(accion, "guardar")) { //00191322 verifica si la accion es guardas
                if(txtClienteNombre.getText().equals("") //00191322 verificamos si el campo nombre se encuentra vacio en el formulario de cliente
                        || txtClienteTelefono.getText().equals("") //00191322 verificamos si el campo telefono se encuentra vacio en el formulario de cliente
                        || txtAreaDireccion.getText().equals("") ){ //00191322 verificamos si el campo direccion se encuentra vacio en el formulario de cliente
                    lanzarAlerta("Rellene todos los campos", Alert.AlertType.WARNING); //00191322 alerta en caso que algun campo requerido este vacio
                    return false; //00191322 retornar falso porque los campos no estan completos
                }
            } else if (Objects.equals(accion, "eliminar")) { //00191322 verifica si la accion es eliminar
                if(txtClienteId.getText().equals("")) { //00191322 verifica si el campo de id del cliente esta vacio
                    lanzarAlerta("Seleccione un Cliente a eliminar", Alert.AlertType.WARNING); //00191322 llama al metodo para mostror una alerta
                    return false; //00191322 retorna falso porque el id del cliente no esta seleccionado
                }
            } else if (Objects.equals(accion, "buscar")) { //00191322 verifica si la acción es buscar
                if(txtBuscarCliente.getText().equals("")) { //00191322 verifica si el campo de bysqueda de cliente esta vacío
                    lanzarAlerta("Ingrese un termino para buscar el Cliente", Alert.AlertType.WARNING); //00191322 alerta en caso que algun campo requerido este vacio
                    return false; //00191322 retornar falso porque los campos no estan completos

                }
            }
        } else if(Objects.equals(modelo, "tarjeta")) { //00191322 verifica si el modelo es tarjeta
            if(Objects.equals(accion, "guardar")) { //00191322 verifica si la accion es guardar
                if(txtNumTarjeta.getText().equals("") //00191322 verifica si el campo numtarjeta esta vacio en el formulario de tarjeta
                        || txtFechaVencimiento.getText().equals("") //00191322 verifica si el campo fechavencimiento  esta vacio en el formulario de tarjeta
                        || txtCvc.getText().equals("") //00191322 verifica si el campo cvc esta vacio en el formulario de tarjeta
                        || cbFacilitador.getSelectionModel().getSelectedItem() == null //00191322 verifica si el campo facilitador esta vacio en el formulario de tarjeta
                        || cbTipoTarjeta.getSelectionModel().getSelectedItem() == null //00191322 verifica si el campo tipotarjeta esta vacio en el formulario de tarjeta
                        || cbClientes.getSelectionModel().getSelectedItem() == null //00191322 verifica si el campo cliente esta vacío en el formulario de tarjeta
                ){
                    lanzarAlerta("Rellene todos los campos", Alert.AlertType.WARNING); //00191322 alerta en caso que algun campo requerido este vacio
                    return false; //00191322 retornar falso porque los campos no estan completos
                }
            } else if (Objects.equals(accion, "eliminar")) { //00191322 verifica si la accion es eliminar
                if(txtTarjetaId.getText().equals("")) { //00191322 verifica si el modelo es buscar
                    lanzarAlerta("Seleccione una Tarjeta a eliminar", Alert.AlertType.WARNING); //00191322 alerta en caso que algun campo requerido este vacio
                    return false; //00191322 retornar falso porque los campos no estan completos
                }
            } else if (Objects.equals(accion, "buscar")) { //00191322 verifica si la accion es buscar
                if(txtBuscarTarjeta.getText().equals("")) { //00191322 verifica si el campo buscartarjeta esta vacio en el formulario de tarjeta
                    lanzarAlerta("Ingrese un termino para buscar la tarjeta", Alert.AlertType.WARNING); //00191322 alerta en caso que algun campo requerido este vacio
                    return false; //00191322 retornar falso porque los campos no estan completos
                }
            }
        } else if(Objects.equals(modelo, "compra")) { //00191322 verifica si el modelo es compra
            if(Objects.equals(accion, "guardar")) { //00191322 verifica si el modelo es guardar
                if(txtMonto.getText().equals("") //00191322 verifica si el campo monto esta vacio en el formulario de compra
                        || txtAreaDescripcion.getText().equals("") //00191322 verifica si el campo esta vacio en el formulario de compra
                        || Objects.isNull(datePickerFechaCompra.getValue()) //00191322 verifica si el campo esta vacio en el formulario de compra
                        || cbTarjetas.getSelectionModel().getSelectedItem() == null //00191322 verifica si el campo esta vacio en el formulario de compra
                ){
                    lanzarAlerta("Rellene todos los campos", Alert.AlertType.WARNING); //00191322 alerta en caso que algun campo requerido este vacio
                    return false; //00191322 retornar falso porque los campos no estan completos
                }
            } else if (Objects.equals(accion, "eliminar")) { //00191322 verifica si la accion es eliminar
                if(txtCompraId.getText().equals("")) { //00191322 verifica si el campo monto esta vacio en el formulario de compra
                    lanzarAlerta("Seleccione una Compra a eliminar", Alert.AlertType.WARNING); //00191322 alerta en caso que algun campo requerido este vacio
                    return false; //00191322 retornar falso porque los campos no estan completos
                }
            } else if (Objects.equals(accion, "buscar")) { //00191322 verifica si la accion es buscar
                if(txtBuscarCompra.getText().equals("")) { //00191322
                    lanzarAlerta("Ingrese un termino para buscar la compra", Alert.AlertType.WARNING); //00191322 alerta en caso que algun campo requerido este vacio
                    return false; //00191322 retornar falso porque los campos no estan completos
                }
            }
        }
        return true; //00191322 retorna verdadero si todos los campos requeridos están completos
    }


    @FXML
    public void obtenerReporteA() {
        System.out.println(reporteCliente.getValue().getNombre());
        System.out.println(reporteAFechaInicio.getValue());
        System.out.println(reporteAFechaFin.getValue());

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date dateInicio = formato.parse(String.valueOf(reporteAFechaInicio.getValue()));
            java.sql.Date fechaInicio = new java.sql.Date(dateInicio.getTime());
            java.util.Date dateFin = formato.parse(String.valueOf(reporteAFechaFin.getValue()));
            java.sql.Date fechaFin = new java.sql.Date(dateFin.getTime());

            ReporteParametro reporteParametro = new ReporteParametro(reporteCliente.getValue().getId(), fechaInicio, fechaFin);
            ReporteA reporteA = new ReporteA();

            reporteA.generarConsulta(reporteParametro);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarComboClientes() {
        try {
            String query = "SELECT * FROM cliente";
            Statement stmt = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            listadoClientesCombo = new ArrayList<Cliente>();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"));

                reporteCliente.getItems().add(cliente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    private LocalDate stringALocalDate(String fechaString) {
        return LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}