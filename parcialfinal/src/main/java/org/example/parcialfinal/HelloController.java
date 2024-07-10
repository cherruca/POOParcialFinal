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
    protected void agregarCliente() {
        String accion;
        if(verificarCamposConDatos("cliente", "guardar")) {
            Cliente cliente = new Cliente();

            if (Objects.equals(txtClienteId.getText(), "")) {
                cliente.setId(null);
                accion = "guardado";
            } else {
                cliente.setId(Integer.valueOf(txtClienteId.getText()));
                accion = "actualizado";
            }

            cliente.setNombre(txtClienteNombre.getText());
            cliente.setTelefono(txtClienteTelefono.getText());
            cliente.setDireccion(txtAreaDireccion.getText());

            if(clienteControlador.persistirCliente(cliente)) {
                lanzarAlerta("Exito", "Cliente " + accion, Alert.AlertType.INFORMATION);
                limpiarCampos("cliente");
                obtenerClientes();
            } else {
                lanzarAlerta("Cliente no guardado", "Hubo un error al guardar el registro", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    protected void eliminarCliente() {
        if(verificarCamposConDatos("cliente", "eliminar")){
            if(clienteControlador.eliminarCliente(Integer.parseInt(txtClienteId.getText()))) {
                lanzarAlerta("Exito", "Cliente eliminado", Alert.AlertType.INFORMATION);
                limpiarCampos("cliente");
                obtenerClientes();
            } else {
                lanzarAlerta("Cliente no eliminado", "Hubo un error al eliminar el registro", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    public void agregarTarjeta() {
        String accion;
        if(verificarCamposConDatos("tarjeta", "guardar")){
            Tarjeta tarjeta = new Tarjeta();
            if (Objects.equals(txtTarjetaId.getText(), "")) {
                tarjeta.setId(null);
                accion = "guardada";
            } else {
                tarjeta.setId(Integer.valueOf(txtTarjetaId.getText()));
                accion = "actualizada";
            }

            tarjeta.setNumero(txtNumTarjeta.getText());
            tarjeta.setFechaVencimiento(txtFechaVencimiento.getText());
            tarjeta.setCodigo(txtCvc.getText());
            tarjeta.setTipoTarjeta(cbTipoTarjeta.getValue());
            tarjeta.setFacilitadorId(cbFacilitador.getSelectionModel().getSelectedItem().getId());
            tarjeta.setClienteId(cbClientes.getSelectionModel().getSelectedItem().getId());

            if(tarjetaControlador.persistirTarjeta(tarjeta)) {
                lanzarAlerta("Exito", "Tarjeta " + accion, Alert.AlertType.INFORMATION);
                limpiarCampos("tarjeta");
                obtenerTarjetas();
            } else {
                lanzarAlerta("Tarjeta no guardada", "Hubo un error al guardar el registro", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    protected void eliminarTarjeta() {
        if(verificarCamposConDatos("tarjeta", "eliminar")){
            if(tarjetaControlador.eliminarTarjeta(Integer.parseInt(txtTarjetaId.getText()))) {
                lanzarAlerta("Exito", "Tarjeta eliminada", Alert.AlertType.INFORMATION);
                limpiarCampos("tarjeta");
                obtenerTarjetas();
            } else {
                lanzarAlerta("Tarjeta no eliminada", "Hubo un error al eliminar el registro", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    public void agregarCompra() {
        String accion;
        if(verificarCamposConDatos("compra", "guardar")){
            Compra compra = new Compra();
            if (Objects.equals(txtCompraId.getText(), "")) {
                compra.setId(null);
                accion = "guardada";
            } else {
                compra.setId(Integer.valueOf(txtCompraId.getText()));
                accion = "actualizada";
            }

            compra.setMonto(Double.valueOf(txtMonto.getText()));
            compra.setDescripcion(txtAreaDescripcion.getText());
            compra.setFechaDeCompra(String.valueOf(datePickerFechaCompra.getValue()));
            compra.setIdTarjeta(cbTarjetas.getSelectionModel().getSelectedItem().getId());

            if(compraControlador.persistirCompra(compra)) {
                lanzarAlerta("Exito", "Compra " + accion, Alert.AlertType.INFORMATION);
                limpiarCampos("compra");
                comboboxVistaCompras();
                obtenerTarjetas();
            } else {
                lanzarAlerta("Compra no guardada", "Hubo un error al guardar el registro", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    protected void eliminarCompra() {
        if(verificarCamposConDatos("compra", "eliminar")){
            if(compraControlador.eliminarCompra(Integer.parseInt(txtCompraId.getText()))) {
                lanzarAlerta("Exito", "Compra eliminada", Alert.AlertType.INFORMATION);
                limpiarCampos("compra");
                obtenerTarjetas();
            } else {
                lanzarAlerta("Compra no eliminada", "Hubo un error al eliminar el registro", Alert.AlertType.ERROR);
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
    private void limpiarCampos(String modelo) {
        if(Objects.equals(modelo, "cliente")) {
            txtClienteId.clear();
            txtClienteNombre.clear();
            txtClienteTelefono.clear();
            txtAreaDireccion.clear();
        } else if (Objects.equals(modelo, "tarjeta")) {
            txtTarjetaId.clear();
            txtNumTarjeta.clear();
            txtFechaVencimiento.clear();
            txtCvc.clear();
            cbClientes.setConverter(null);
            cbTipoTarjeta.setConverter(null);
            cbFacilitador.setConverter(null);
            cbClientes.valueProperty().set(null);
            cbTipoTarjeta.valueProperty().set(null);
            cbFacilitador.valueProperty().set(null);
            comboboxVistaTarjetas();
        } else if (Objects.equals(modelo, "tarjeta-campos")) {
            txtTarjetaId.clear();
            txtNumTarjeta.clear();
            txtFechaVencimiento.clear();
            txtCvc.clear();
            cbClientes.valueProperty().set(null);
            cbTipoTarjeta.valueProperty().set(null);
            cbFacilitador.valueProperty().set(null);
        } else if (Objects.equals(modelo, "compra")) {
            txtCompraId.clear();
            txtMonto.clear();
            txtAreaDescripcion.clear();
            datePickerFechaCompra.setValue(null);
            cbTarjetas.setConverter(null);
            cbTarjetas.valueProperty().set(null);
            comboboxVistaCompras();
        }
    }

    private boolean verificarCamposConDatos(String modelo, String accion){
        if(Objects.equals(modelo, "cliente")) {
            if(Objects.equals(accion, "guardar")) {
                if(txtClienteNombre.getText().equals("")
                        || txtClienteTelefono.getText().equals("")
                        || txtAreaDireccion.getText().equals("") ){
                    lanzarAlerta("Rellene todos los campos", Alert.AlertType.WARNING);
                    return false;
                }
            } else if (Objects.equals(accion, "eliminar")) {
                if(txtClienteId.getText().equals("")) {
                    lanzarAlerta("Seleccione un Cliente a eliminar", Alert.AlertType.WARNING);
                    return false;
                }
            } else if (Objects.equals(accion, "buscar")) {
                if(txtBuscarCliente.getText().equals("")) {
                    lanzarAlerta("Ingrese un termino para buscar el Cliente", Alert.AlertType.WARNING);
                    return false;
                }
            }
        } else if(Objects.equals(modelo, "tarjeta")) {
            if(Objects.equals(accion, "guardar")) {
                if(txtNumTarjeta.getText().equals("")
                        || txtFechaVencimiento.getText().equals("")
                        || txtCvc.getText().equals("")
                        || cbFacilitador.getSelectionModel().getSelectedItem() == null
                        || cbTipoTarjeta.getSelectionModel().getSelectedItem() == null
                        || cbClientes.getSelectionModel().getSelectedItem() == null
                ){
                    lanzarAlerta("Rellene todos los campos", Alert.AlertType.WARNING);
                    return false;
                }
            } else if (Objects.equals(accion, "eliminar")) {
                if(txtTarjetaId.getText().equals("")) {
                    lanzarAlerta("Seleccione una Tarjeta a eliminar", Alert.AlertType.WARNING);
                    return false;
                }
            } else if (Objects.equals(accion, "buscar")) {
                if(txtBuscarTarjeta.getText().equals("")) {
                    lanzarAlerta("Ingrese un termino para buscar la tarjeta", Alert.AlertType.WARNING);
                    return false;
                }
            }
        } else if(Objects.equals(modelo, "compra")) {
            if(Objects.equals(accion, "guardar")) {
                if(txtMonto.getText().equals("")
                        || txtAreaDescripcion.getText().equals("")
                        || Objects.isNull(datePickerFechaCompra.getValue())
                        || cbTarjetas.getSelectionModel().getSelectedItem() == null
                ){
                    lanzarAlerta("Rellene todos los campos", Alert.AlertType.WARNING);
                    return false;
                }
            } else if (Objects.equals(accion, "eliminar")) {
                if(txtCompraId.getText().equals("")) {
                    lanzarAlerta("Seleccione una Compra a eliminar", Alert.AlertType.WARNING);
                    return false;
                }
            } else if (Objects.equals(accion, "buscar")) {
                if(txtBuscarCompra.getText().equals("")) {
                    lanzarAlerta("Ingrese un termino para buscar la compra", Alert.AlertType.WARNING);
                    return false;
                }
            }
        }
        return true;
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