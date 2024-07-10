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
import org.example.parcialfinal.modelo.*;


import java.net.URL;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import java.util.*;

public class HelloController implements Initializable {
    @FXML // 00402523 elemento de FXML
    private Button btnBuscarCliente; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es buscar un cliente

    @FXML // 00402523 elemento de FXML
    private Button btnBuscarCompra; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es buscar una compra

    @FXML // 00402523 elemento de FXML
    private Button btnBuscarTarjeta; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es buscar una tarjeta

    @FXML // 00402523 elemento de FXML
    private Button btnEliminarCliente; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es eliminar un cliente

    @FXML // 00402523 elemento de FXML
    private Button btnEliminarCompra; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es eliminar una compra

    @FXML // 00402523 elemento de FXML
    private Button btnEliminarTarjeta; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es eliminar una tarjeta

    @FXML // 00402523 elemento de FXML
    private Button btnGuardarCliente; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es guardar un cliente

    @FXML // 00402523 elemento de FXML
    private Button btnGuardarCompra; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es guardar una compra

    @FXML // 00402523 elemento de FXML
    private Button btnGuardarTarjeta; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es guardar una tarjeta

    @FXML // 00402523 elemento de FXML
    private ComboBox<Cliente> cbClientes; // 00191322 Combobox que de tipo Cliente

    @FXML // 00402523 elemento de FXML
    private ComboBox<Facilitador> cbFacilitador; // 00191322 Combobox que de tipo Facilitador

    @FXML // 00402523 elemento de FXML
    private ComboBox<Tarjeta> cbTarjetas; // 00191322 Combobox que de tipo Tarjeta

    @FXML // 00402523 elemento de FXML
    private ComboBox<String> cbTipoTarjeta; // 00191322 Combobox que de tipo String (Tipo Tarjeta, ya sea Debito o Credito)

    @FXML // 00402523 elemento de FXML
    private DatePicker datePickerFechaCompra; // 00191322 Datepicker para elegir la fecha de la compra

    @FXML // 00402523 elemento de FXML
    private TableView<Cliente> tblClientes; // 00191322 TableView que mostrara los clientes traidos desde la base

    @FXML // 00402523 elemento de FXML
    private TableView<Compra> tblCompras; // 00191322 TableView que mostrara las compras traidas desde la base

    @FXML // 00402523 elemento de FXML
    private TableView<Tarjeta> tblTarjetas; // 00191322 TableView que mostrara las tarjetas traidas desde la base

    @FXML // 00402523 elemento de FXML
    private TableColumn<Cliente, String> tcClienteDireccion; // 00191322 Columna de la Tabla de Cliente que representara la direccion del Cliente

    @FXML // 00402523 elemento de FXML
    private TableColumn<Cliente, Integer> tcClienteId; // 00191322 Columna de la Tabla de Cliente que representara el Id del Cliente

    @FXML // 00402523 elemento de FXML
    private TableColumn<Cliente, String> tcClienteNombre; // 00191322 Columna de la Tabla de Cliente que representara el Nombre del Cliente

    @FXML // 00402523 elemento de FXML
    private TableColumn<Cliente, String> tcClienteTelefono; // 00191322 Columna de la Tabla de Cliente que representara el Telefono del Cliente

    @FXML // 00402523 elemento de FXML
    private TableColumn<Compra, String> tcCompraDescripcion; // 00191322 Columna de la Tabla de Compra que representara la Descripcion de la Compra

    @FXML // 00402523 elemento de FXML
    private TableColumn<Compra, String> tcCompraFechaCompra; // 00191322 Columna de la Tabla de Compra que representara la Fecha de la Compra

    @FXML // 00402523 elemento de FXML
    private TableColumn<Compra, Integer> tcCompraId; // 00191322 Columna de la Tabla de Compra que representara el Id de la Compra

    @FXML // 00402523 elemento de FXML
    private TableColumn<Compra, Double> tcCompraMonto; // 00191322 Columna de la Tabla de Compra que representara el Monto de la Compra

    @FXML // 00402523 elemento de FXML
    private TableColumn<Compra, Integer> tcCompraTarjeta; // 00191322 Columna de la Tabla de Compra que representara la Tarjeta usada en la Compra

    @FXML // 00402523 elemento de FXML
    private TableColumn<Tarjeta, String> tcTarjetaCvc; // 00191322 Columna de la Tabla de Tarjeta que representara el codigo CVC de la Tarjeta

    @FXML // 00402523 elemento de FXML
    private TableColumn<Tarjeta, String> tcTarjetaFechaVen; // 00191322 Columna de la Tabla de Tarjeta que representara la Fecha de Vencimiento de la Tarjeta

    @FXML // 00402523 elemento de FXML
    private TableColumn<Tarjeta, Integer> tcTarjetaId; // 00191322 Columna de la Tabla de Tarjeta que representara el Id de la Tarjeta

    @FXML // 00402523 elemento de FXML
    private TableColumn<Tarjeta, Integer> tcTarjetaNomCliente; // 00191322 Columna de la Tabla de Tarjeta que representara el Nombre del Cliente asociado a la Tarjeta

    @FXML // 00402523 elemento de FXML
    private TableColumn<Tarjeta, String> tcTarjetaNum; // 00191322 Columna de la Tabla de Tarjeta que representara el Numero de la Tarjeta

    @FXML // 00402523 elemento de FXML
    private TableColumn<Tarjeta, String> tcTarjetaTipo; // 00191322 Columna de la Tabla de Tarjeta que representara el Tipo de la Tarjeta

    @FXML // 00402523 elemento de FXML
    private TableColumn<Tarjeta, String> tcTarjetaFacilitador; // 00191322 Columna de la Tabla de Tarjeta que representara el Facilitador (Visa, Mastercard, etc) de la Tarjeta

    @FXML // 00402523 elemento de FXML
    private TextArea txtAreaDescripcion; // 00191322 Text Area para Compra donde se introduce la descripcion

    @FXML // 00402523 elemento de FXML
    private TextArea txtAreaDireccion; // 00191322 Text Area para Cliente donde se introduce la direccion

    @FXML // 00402523 elemento de FXML
    private TextField txtClienteId; // 00191322 Text Field para Cliente que contiene el Id (esta oculto en la vista)

    @FXML // 00402523 elemento de FXML
    private TextField txtTarjetaId; // 00191322 Text Field para Tarjeta que contiene el Id (esta oculto en la vista)

    @FXML // 00402523 elemento de FXML
    private TextField txtCompraId; // 00191322 Text Field para Compra que contiene el Id (esta oculto en la vista)

    @FXML // 00402523 elemento de FXML
    private TextField txtBuscarCliente; // 00191322 Text Field para Buscar un Cliente

    @FXML // 00402523 elemento de FXML
    private TextField txtBuscarCompra; // 00191322 Text Field para Buscar una Compra

    @FXML // 00402523 elemento de FXML
    private TextField txtBuscarTarjeta; // 00191322 Text Field para Buscar una Tarjeta

    @FXML // 00402523 elemento de FXML
    private TextField txtClienteNombre; // 00191322 Text Field para Cliente donde se introduce el Nombre

    @FXML // 00402523 elemento de FXML
    private TextField txtClienteTelefono; // 00191322 Text Field para Cliente donde se introduce el Telefono

    @FXML // 00402523 elemento de FXML
    private TextField txtCvc; // 00191322 Text Field para Tarjeta donde se introduce el CVC

    @FXML // 00402523 elemento de FXML
    private TextField txtFechaVencimiento; // 00191322 Text Field para Tarjeta donde se introduce la fecha de vencimiento

    @FXML // 00402523 elemento de FXML
    private TextField txtMonto; // 00191322 Text Field para Compra donde se introduce el Monto

    @FXML // 00402523 elemento de FXML
    private TextField txtNumTarjeta; // 00191322 Text Field para Tarjeta donde se introduce el numero de Tarjeta

    @FXML // 00402523 elemento de FXML
    private VBox vbMenu; // 00191322 Elemento VBox para el menu

    @FXML // 00402523 elemento de FXML
    private Button btnReporteA; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es generar un reporte de tipo A
    @FXML // 00402523 elemento de FXML
    private Button btnReporteB; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es generar un reporte de tipo B
    @FXML // 00402523 elemento de FXML
    private Button btnReporteC; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es generar un reporte de tipo C
    @FXML // 00402523 elemento de FXML
    private Button btnReporteD; // 00191322 Variable de tipo Button que representa un boton en FXML y su accion es generar un reporte de tipo D
    @FXML // 00402523 elemento de FXML
    private DatePicker cbReporteBanio; // 00191322 DatePicker que de tipo Reporte
    @FXML // 00402523 elemento de FXML
    private ComboBox<?> cbReporteBmes; // 00191322 Combobox que de tipo Bmes
    @FXML // 00402523 elemento de FXML
    private ComboBox<Facilitador> cbReporteDfacilitador; // 00191322 Combobox que de tipo Facilitador
    @FXML // 00402523 elemento de FXML
    private DatePicker reporteAFechaFin; // 00191322 Datepicker para elegir la fecha de inicio del reporte
    @FXML // 00402523 elemento de FXML
    private DatePicker reporteAFechaInicio; // 00191322 Datepicker para elegir la fecha fin del reporte
    @FXML // 00402523 elemento de FXML
    private ComboBox<Cliente> reporteCliente; // 00191322 Combobox que de tipo Cliente
    @FXML // 00402523 elemento de FXML
    private TextArea webReporte; // 00191322 textArea para los datos del webReporte

    private ArrayList<Cliente> listadoClientesCombo;

    /**
     * 00191322 ObservableList de las clases Modelo, con estos se actualizan y muestran datos en las tablas y combobox
     */
    ObservableList<Cliente> observableListCliente = FXCollections.observableArrayList(); // 00191322 ObservableList de tipo Cliente, se inicializa como FXCollections.observableArrayList()
    ObservableList<Facilitador> observableListFacilitador = FXCollections.observableArrayList(); // 00191322 ObservableList de tipo Facilitador, se inicializa como FXCollections.observableArrayList()
    ObservableList<Tarjeta> observableListTarjeta = FXCollections.observableArrayList(); // 00191322 ObservableList de tipo Tarjeta, se inicializa como FXCollections.observableArrayList()
    ObservableList<Compra> observableListCompra = FXCollections.observableArrayList(); // 00191322 ObservableList de tipo Compra, se inicializa como FXCollections.observableArrayList()

    ObservableList<String> observableListTipoTarjeta = FXCollections.observableArrayList(); // 00191322 ObservableList de tipo String para TipoTarjeta (Visa, MasterCard, etc), se inicializa como FXCollections.observableArrayList()

    /**
     * 00191322 Se declaran los controladores como atributos y se
     * 00191322 hace una simulacion de Dependency Injection (Inyeccion de Dependencias)
     */
    private ClienteControlador clienteControlador; // 00191322 Atributo privado de tipo Controlador de Cliente
    private CompraControlador compraControlador; // 00191322 Atributo privado de tipo Controlador de Compra
    private TarjetaControlador tarjetaControlador; // 00191322 Atributo privado de tipo Controlador de Tarjeta
    private FacilitadorControlador facilitadorControlador; // 00191322 Atributo privado de tipo Controlador de Facilitador


    // 00191322 Constructor sin argumentos requerido por FXM Loader
    public HelloController() {
        this(new ClienteControlador(), new CompraControlador(), new TarjetaControlador(), new FacilitadorControlador()); // 00191322 Se llama el constructor con argumentos de los controladores para siempre incializar una instancia con los atributos seteados
    }

    /**
     * 00191322 Se inicializan las instancias de los controladores
     * 00191322 por medio de un constructor de la clase
     * @param clienteControlador instancia de ClienteControlador a ser inyectada
     */
    public HelloController(ClienteControlador clienteControlador, CompraControlador compraControlador, TarjetaControlador tarjetaControlador, FacilitadorControlador facilitadorControlador) { // 00191322 Constructor con argumentos que incializa una instancia de cada controlador
        this.clienteControlador = clienteControlador; // 00191322 se asigna un valor al atributo del controlador Cliente
        this.compraControlador = compraControlador; // 00191322 se asigna un valor al atributo del controlador Compra
        this.tarjetaControlador = tarjetaControlador; // 00191322 se asigna un valor al atributo del controlador Tarjeta
        this.facilitadorControlador = facilitadorControlador; // 00191322 se asigna un valor al atributo del controlador Facilitador
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // 00191322 Metodo que se ejectura al iniciar el projecto
        obtenerTarjetas(); // 00191322 se obtienen los datos de Tarjetas de la base
        obtenerClientes(); // 00191322 se obtienen los datos de Clientes de la base
        obtenerFacilitadores(); // 00191322 se obtienen los datos de Facilitadores de la base
        obtenerCompras(); // 00191322 se obtienen los datos de Compras de la base
        /**
         * 00191322 Se obtiene el header de la tabla usando un listener,
         * 00191322 luego se agrega otro listener a la propiedad de reordering (reordenamiento)
         * 00191322 y se asigan false al atributo Reordering
         *
         * 00191322 Con esto, se logra que las columnas de las tablas no se pueden ajustar su tamaño
         */

        tblClientes.widthProperty().addListener((observableValue, number, t1) -> { // 00191322 se obtiene la widthProperty, luego se le asigna un listener por medio de una lambda
            TableHeaderRow header = (TableHeaderRow) tblClientes.lookup("TableHeaderRow"); // 00191322 se obtiene la TableHeaderRow
            header.reorderingProperty().addListener((observableValue1, aBoolean, t11) -> header.setReordering(false)); // 0191322 se utiliza una lambda para asignarle un reordering false type al header
        });

        tblTarjetas.widthProperty().addListener((observableValue, number, t1) -> { // 00191322 se obtiene la widthProperty, luego se le asigna un listener por medio de una lambda
            TableHeaderRow header = (TableHeaderRow) tblTarjetas.lookup("TableHeaderRow"); // 00191322 se obtiene la TableHeaderRow
            header.reorderingProperty().addListener((observableValue1, aBoolean, t11) -> header.setReordering(false)); // 0191322 se utiliza una lambda para asignarle un reordering false type al header
        });

        tblCompras.widthProperty().addListener((observableValue, number, t1) -> { // 00191322 se obtiene la widthProperty, luego se le asigna un listener por medio de una lambda
            TableHeaderRow header = (TableHeaderRow) tblCompras.lookup("TableHeaderRow"); // 00191322 se obtiene la TableHeaderRow
            header.reorderingProperty().addListener((observableValue1, aBoolean, t11) -> header.setReordering(false)); // 0191322 se utiliza una lambda para asignarle un reordering false type al header
        });

        /**
         * 00191322 Se asigna un value factory a las columnas para
         * 00191322 definir que atributo van a mostrar
         */
        tcClienteId.setCellValueFactory(new PropertyValueFactory<>("id")); // 00191322 se asigna a la columna el atributo de id
        tcClienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); // 00191322 se asigna a la columna el atributo de nombre
        tcClienteTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono")); // 00191322 se asigna a la columna el atributo de telefono
        tcClienteDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion")); // 00191322 se asigna a la columna el atributo de direccion

        tcTarjetaId.setCellValueFactory(new PropertyValueFactory<>("id")); // 00191322 se asigna a la columna el atributo de id
        tcTarjetaNomCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente")); // 00191322 se asigna a la columna el atributo de nombreCliente
        tcTarjetaNum.setCellValueFactory(new PropertyValueFactory<>("numero")); // 00191322 se asigna a la columna el atributo de numero
        tcTarjetaCvc.setCellValueFactory(new PropertyValueFactory<>("codigo")); // 00191322 se asigna a la columna el atributo de codigo
        tcTarjetaFechaVen.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento")); // 00191322 se asigna a la columna el atributo de fechaVencimiento
        tcTarjetaFacilitador.setCellValueFactory(new PropertyValueFactory<>("nombreFacilitador")); // 00191322 se asigna a la columna el atributo de nombreFacilitador
        tcTarjetaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoTarjeta")); // 00191322 se asigna a la columna el atributo de tipoTarjeta

        tcCompraId.setCellValueFactory(new PropertyValueFactory<>("id")); // 00191322 se asigna a la columna el atributo de id
        tcCompraMonto.setCellValueFactory(new PropertyValueFactory<>("monto")); // 00191322 se asigna a la columna el atributo de monto
        tcCompraTarjeta.setCellValueFactory(new PropertyValueFactory<>("clienteTarjeta")); // 00191322 se asigna a la columna el atributo de clienteTarjeta
        tcCompraDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion")); // 00191322 se asigna a la columna el atributo de descripcion
        tcCompraFechaCompra.setCellValueFactory(new PropertyValueFactory<>("fechaDeCompra")); // 00191322 se asigna a la columna el atributo de fechaDeCompra

        /**
         * 00191322 Se asigna datos a la tabla por medio de un ObservableList
         */
        tblClientes.setItems(observableListCliente); // 00191322 se le asigna a la table Clientes el observableListCliente
        tblTarjetas.setItems(observableListTarjeta); // 00191322 se le asigna a la table Tarjetas el observableListTarjeta
        tblCompras.setItems(observableListCompra); // 00191322 se le asigna a la table Compras el observableListCompra

        tblClientes.setRowFactory(object -> { // 00191322 se usa lambda para la tabla Clientes
            TableRow<Cliente> row = new TableRow<>(); // 00191322 se crea un objeto de tipo TableRow<Cliente>
            row.setOnMouseClicked(event -> { // 00191322 se registra el evento setOnMouseClicked y se asigna su comportamiento con una lambda
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) { // 00191322 se evalua que la row no este empty y el click sea con el boton primary del mouse (izquierdo)
                    Cliente cliente = row.getItem(); // 00191322 Si se paso la evaluacion, se crea una instancia Cliente obteniendo el item de la row seleccionada
                    if(row.getIndex() == tblClientes.getSelectionModel().getSelectedIndex() // 00191322 se evalua si la row que se seleccinoa ya esta seleccionada
                            && Objects.equals(txtClienteId.getText(), String.valueOf(cliente.getId())) // 00191322 tambien se evalua si el valor del textField id es el mismo de la row seleccionada
                    ){
                        tblClientes.getSelectionModel().clearSelection(); // 00191322 si la evaluacion anterior es true, se deselecciona el item
                        limpiarCampos("cliente"); // 00191322 y tambien se limpian los campos
                    } else {
                        txtClienteId.setText(cliente.getId().toString()); // 00191322 se asigna al textField el valor de Id
                        txtClienteNombre.setText(cliente.getNombre()); // 00191322 se asigna al textField el valor de Nombre
                        txtClienteTelefono.setText(cliente.getTelefono()); // 00191322 se asigna al textField el valor de Telefono
                        txtAreaDireccion.setText(cliente.getDireccion()); // 00191322 se asigna al textArea el valor de Direccion
                    }
                }
            });
            return row; // 00191322 se devuelve la row a la lambda con las propiedades asignadas
        });

        tblTarjetas.setRowFactory(object -> { // 00191322 se usa lambda para la tabla Tarjetas
            TableRow<Tarjeta> row = new TableRow<>(); // 00191322 se crea un objeto de tipo TableRow<Tarjeta>
            row.setOnMouseClicked(event -> { // 00191322 se registra el evento setOnMouseClicked y se asigna su comportamiento con una lambda
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) { // 00191322 se evalua que la row no este empty y el click sea con el boton primary del mouse (izquierdo)
                    Tarjeta tarjeta = row.getItem(); // 00191322 Si se paso la evaluacion, se crea una instancia Tarjeta obteniendo el item de la row seleccionada
                    if(row.getIndex() == tblTarjetas.getSelectionModel().getSelectedIndex() // 00191322 se evalua si la row que se seleccinoa ya esta seleccionada
                            && Objects.equals(txtTarjetaId.getText(), String.valueOf(tarjeta.getId())) // 00191322 tambien se evalua si el valor del textField id es el mismo de la row seleccionada
                    ){
                        tblTarjetas.getSelectionModel().clearSelection(); // 00191322 si la evaluacion anterior es true, se deselecciona el item
                        limpiarCampos("tarjeta"); // 00191322 y tambien se limpian los campos
                    } else {
                        txtTarjetaId.setText(tarjeta.getId().toString()); // 00191322 se asigna al textField el valor de Id
                        txtNumTarjeta.setText(tarjeta.getNumero()); // 00191322 se asigna al textField el valor de Numero
                        txtFechaVencimiento.setText(tarjeta.getFechaVencimiento()); // 00191322 se asigna al textField el valor de Fecha de Vencimiento
                        txtCvc.setText(tarjeta.getCodigo()); // 00191322 se asigna al textField el valor de Cvc (codigo)
                        cbTipoTarjeta.setValue(tarjeta.getTipoTarjeta()); // 00191322 se asigna al Combobox el valor de Tipo de Tarjeta
                        cbClientes.setValue(clienteControlador.buscarCliente(tarjeta.getClienteId())); // 00191322 se asigna al Combobox el valor de Clientes
                        cbFacilitador.setValue(facilitadorControlador.obtenerFacilitador(tarjeta.getFacilitadorId())); // 00191322 se asigna al Combobox el valor de Facilitador
                    }
                }
            });
            return row; // 00191322 se devuelve la row a la lambda con las propiedades asignadas
        });

        tblCompras.setRowFactory(object -> { // 00191322 se usa lambda para la tabla Compras
            TableRow<Compra> row = new TableRow<>(); // 00191322 se crea un objeto de tipo TableRow<Compra>
            row.setOnMouseClicked(event -> { // 00191322 se registra el evento setOnMouseClicked y se asigna su comportamiento con una lambda
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) { // 00191322 se evalua que la row no este empty y el click sea con el boton primary del mouse (izquierdo)
                    Compra compra = row.getItem(); // 00191322 Si se paso la evaluacion, se crea una instancia Compra obteniendo el item de la row seleccionada
                    if(row.getIndex() == tblCompras.getSelectionModel().getSelectedIndex() // 00191322 se evalua si la row que se seleccinoa ya esta seleccionada
                            && Objects.equals(txtCompraId.getText(), String.valueOf(compra.getId())) // 00191322 tambien se evalua si el valor del textField id es el mismo de la row seleccionada
                    ){
                        tblCompras.getSelectionModel().clearSelection(); // 00191322 si la evaluacion anterior es true, se deselecciona el item
                        limpiarCampos("compra"); // 00191322 y tambien se limpian los campos
                    } else {
                        txtCompraId.setText(compra.getId().toString()); // 00191322 se asigna al textField el valor de Id
                        txtMonto.setText(compra.getMonto().toString()); // 00191322 se asigna al textField el valor de Monto
                        datePickerFechaCompra.setValue(stringALocalDate(compra.getFechaDeCompra())); // 00191322 se asigna al DatePicker el valor de Fecha de Compra
                        txtAreaDescripcion.setText(compra.getDescripcion()); // 00191322 se asigna al textArea el valor de Descripcion
                        cbTarjetas.setValue(tarjetaControlador.obtenerTarjeta(compra.getIdTarjeta())); // 00191322 se asigna al Combobox el valor de Tarjetas
                    }
                }
            });
            return row; // 00191322 se devuelve la row a la lambda con las propiedades asignadas
        });

        cbFacilitador.setItems(observableListFacilitador); // 00191322 se asigna al Combobox todos los Facilitadores
        cbClientes.setItems(observableListCliente); // 00191322 se asigna al Combobox todas los Clientes
        cbTarjetas.setItems(observableListTarjeta); // 00191322 se asigna al Combobox todas las Tarjetas

        List<String> tipoTarjeta = new ArrayList<>(); // 00191322 se crea una Lista de tipo String para los Tipo de Tarjeta
        tipoTarjeta.add("Debito"); // 00191322 se agrega la opcion Debito a la lista
        tipoTarjeta.add("Credito"); // 00191322 se agrega la opcion Credito a la lista

        observableListTipoTarjeta.addAll(tipoTarjeta); // 00191322 se asigna el valor de la lista de Tipo Tarjeta al observableListTipoTarjeta

        cbTipoTarjeta.setItems(observableListTipoTarjeta); // 00191322 se asigna al Combobox todas los Tipo de Tarjeta, en este caso Credito y Debito

        cargarComboClientes(); // 00402523 rellena el combo de clientes para generar los reportes
        cargarComboFacilitadores(); // 00402523 rellena el combo de clientes para generar los reportes
    }

    @FXML // 00402523 elemento de FXML
    public void vistaClientes() { // 00191322 metodo que se ejecuta cada que se cambie a la pestana clientes
        limpiarCampos("cliente"); // 00191322 se limpian los campos de clientes
        obtenerClientes(); // 00191322 se obtienen toods los clientes de la base



    }

    @FXML // 00402523 elemento de FXML
    public void vistaCompras(){  // 00191322 metodo que se ejecuta cada que se cambie a la pestana compras
        limpiarCampos("compra"); // 00191322 se limpian los campos de compras
        comboboxVistaCompras(); // 00191322 metodo que borra los elementos del combobox y asigna setConverter
    }

    public void comboboxVistaCompras() { // 00191322 metodo para cargar los combobox apropiadamente
        obtenerCompras(); // 00191322 se obtienen todas las compras de la base
        cbTarjetas.setConverter(new StringConverter<Tarjeta>() { // 00191322 se crea una clase anonima de tipo StringConverter
            @Override
            public String toString(Tarjeta object) { // 00191322 se hace override al metodo toString, y se le manda un objeto de tipo Tarjeta
                return object.getNumero(); // 00191322 se devuelve el atributo del objeto que se mostrara en el combobox
            }

            @Override
            public Tarjeta fromString(String string) { // 00191322 se hace override al metodo fromString
                return cbTarjetas.getItems().stream().filter(tarjeta -> // 00191322 se obtienen los items del combobox y usando la API stream se usa una lambda
                        tarjeta.getNumero().equals(string)).findFirst().orElse(null); // 00191322 que luego evalua si el numero es igual a algun elemento en el bombox o si no se asigna null
            }
        });
    }

    @FXML // 00402523 elemento de FXML
    public void vistaTarjetas(){ // 00191322 metodo que se ejecuta cada que se cambie a la pestana tarjetas
        limpiarCampos("tarjeta"); // 00191322 se limpian los campos de tarjetas
        comboboxVistaTarjetas(); // 00191322 metodo que borra los elementos del combobox y asigna setConverter
    }

    public void comboboxVistaTarjetas() { // 00191322 metodo para cargar los combobox apropiadamente
        obtenerTarjetas(); // 00191322 se obtienen todas las tarjetas de la base
        obtenerClientes(); // 00191322 se obtienen todas las clientes de la base
        obtenerFacilitadores(); // 00191322 se obtienen todas las facilitadores de la base

        cbFacilitador.setConverter(new StringConverter<Facilitador>() { // 00191322 se crea una clase anonima de tipo StringConverter

            @Override
            public String toString(Facilitador object) { // 00191322 se hace override al metodo toString, y se le manda un objeto de tipo Facilitador
                return object.getTipo(); // 00191322 se devuelve el atributo del objeto que se mostrara en el combobox
            }

            @Override
            public Facilitador fromString(String string) { // 00191322 se hace override al metodo fromString
                return cbFacilitador.getItems().stream().filter(facilitador -> // 00191322 se obtienen los items del combobox y usando la API stream se usa una lambda
                        facilitador.getTipo().equals(string)).findFirst().orElse(null); // 00191322 que luego evalua si el numero es igual a algun elemento en el bombox o si no se asigna null
            }
        });

        cbClientes.setConverter(new StringConverter<Cliente>() { // 00191322 se crea una clase anonima de tipo StringConverter

            @Override
            public String toString(Cliente object) { // 00191322 se hace override al metodo toString, y se le manda un objeto de tipo Cliente
                return object.getNombre(); // 00191322 se devuelve el atributo del objeto que se mostrara en el combobox
            }

            @Override
            public Cliente fromString(String string) { // 00191322 se hace override al metodo fromString
                return cbClientes.getItems().stream().filter(cliente -> // 00191322 se obtienen los items del combobox y usando la API stream se usa una lambda
                        cliente.getNombre().equals(string)).findFirst().orElse(null); // 00191322 que luego evalua si el numero es igual a algun elemento en el bombox o si no se asigna null
            }
        });
    }

    @FXML // 00402523 elemento de FXML
    public List<Cliente> obtenerClientes() { // 00191322 Metodo para obtener todos los Clientes de la base
        // 00191322 Se obtiene los registros de los clientes de la base de datos
        List<Cliente> clientes = clienteControlador.obtenerClientes();
        // 00191322 Se limpia el ObservableList
        observableListCliente.clear();
        // 00191322 Se añade la lista al ObservableList
        observableListCliente.addAll(clientes);
        // 00191322 se retorna la lista de Clientes
        return clientes;
    }

    @FXML // 00402523 elemento de FXML
    public List<Facilitador> obtenerFacilitadores() { // 00191322 Metodo para obtener todos los Facilitadores de la base
        // 00191322 Se obtiene los registros de los clientes de la base de datos
        List<Facilitador> facilitadores = facilitadorControlador.obtenerFacilitadores();
        // 00191322 Se limpia el ObservableList
        observableListFacilitador.clear();
        // 00191322 Se añade la lista al ObservableList
        observableListFacilitador.addAll(facilitadores);
        // 00191322 se retorna la lista de Facilitadores
        return facilitadores;
    }

    @FXML // 00402523 elemento de FXML
    public List<Compra> obtenerCompras(){ // 00191322 Metodo para obtener todas los Compras de la base
        // 00191322 Se obtiene los registros de las compras de la base de datos
        List<Compra> compras = compraControlador.obtenerCompras();
        // 00191322 Se limpia el ObservableList
        observableListCompra.clear();
        // 00191322 Se añade la lista al ObservableList
        observableListCompra.addAll(compras);
        // 00191322 se retorna la lista de Compras
        return compras;
    }

    @FXML // 00402523 elemento de FXML
    public List<Tarjeta> obtenerTarjetas() { // 00191322 Metodo para obtener todas los Tarjetas de la base
        // 00191322 Se obtiene los registros de las tarjetas de la base de datos
        List<Tarjeta> tarjetas = tarjetaControlador.obtenerTarjetas();
        // 00191322 Se limpia el ObservableList
        observableListTarjeta.clear();
        // 00191322 Se añade la lista al ObservableList
        observableListTarjeta.addAll(tarjetas);
        // 00191322 se retorna la lista de Tarjetas
        return tarjetas;
    }

    @FXML // 00402523 elemento de FXML
    protected void buscarCliente() { // 00191322 metodo para buscar Clientes
        String termino = txtBuscarCliente.getText(); // 00191322 se obtiene el termino de busca del TextField txtBuscarCliente
        if(verificarCamposConDatos("cliente", "buscar")) { // 00191322 se evalua que el TextField no este vacio
            List<Cliente> clientes = clienteControlador.buscarClientes(termino); // 00191322 se obtienen los clientes que se encontraron en la busqueda
            observableListCliente.clear(); // 00191322 se limpian los registros de clientes del observableListCliente
            observableListCliente.addAll(clientes); // 00191322 se agregan los nuevos registros encontrados
        }
    }

    @FXML // 00402523 elemento de FXML
    protected void buscarTarjeta() { // 00191322 metodo para buscar Tarjetas
        String termino = txtBuscarTarjeta.getText(); // 00191322 se obtiene el termino de busca del TextField txtBuscarTarjeta
        if(verificarCamposConDatos("tarjeta", "buscar")) { // 00191322 se evalua que el TextField no este vacio
            List<Tarjeta> tarjetas = tarjetaControlador.buscarTarjetas(termino); // 00191322 se obtienen las tarjetas que se encontraron en la busqueda
            observableListTarjeta.clear(); // 00191322 se limpian los registros de clientes del observableListTarjeta
            observableListTarjeta.addAll(tarjetas); // 00191322 se agregan los nuevos registros encontrados
        }
    }

    @FXML // 00402523 elemento de FXML
    protected void buscarCompra() { // 00191322 metodo para buscar Compras
        String termino = txtBuscarCompra.getText(); // 00191322 se obtiene el termino de busca del TextField txtBuscarCompra
        if(verificarCamposConDatos("compra", "buscar")) { // 00191322 se evalua que el TextField no este vacio
            List<Compra> compras = compraControlador.buscarCompras(termino); // 00191322 se obtienen las compras que se encontraron en la busqueda
            observableListCompra.clear(); // 00191322 se limpian los registros de compras del observableListCompra
            observableListCompra.addAll(compras); // 00191322 se agregan los nuevos registros encontrados
        }
    }

    @FXML // 00402523 elemento de FXML
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

    @FXML // 00402523 elemento de FXML
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

    @FXML // 00402523 elemento de FXML
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

    @FXML // 00402523 elemento de FXML
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

    @FXML // 00402523 elemento de FXML
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

    @FXML // 00402523 elemento de FXML
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
     * 00191322 Metodo que nos permitira lanzar alertas al usuario
     *
     * @param mensaje mensaje a mostrar en nuestra alerta
     * @param tipo tipo de alerta en base al enum estatico AlertType de la clase Alert
     *             cuyos valores pueden ser: NONE, INFORMATION, WARNING, CONFIRMATION, y ERROR
     */
    private void lanzarAlerta(String mensaje, Alert.AlertType tipo) {
        // 00191322 Definimos el tipo de alerta
        Alert alert = new Alert(tipo);
        // 00191322 Titulo de nuestra ventana
        alert.setTitle("Mensaje del sistema");
        // 00191322 Asignamos el valor del header, en este caso null
        alert.setHeaderText(null);
        // 00191322 El mensaje de contendra nuestra ventana
        alert.setContentText(mensaje);
        // 00191322 Metodo que bloquea las demas ventanas y espera hasta que el usario interactue con el cuadro de dialogo
        alert.showAndWait();
    }

    /**
     * 00191322 Se hace un overloading de lanzarAlerta() teniendo
     * 00191322 un metodo con 3 parametros y otro con 2 parametros
     * @param titulo
     * @param mensaje
     * @param tipo
     */
    private void lanzarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        // 00191322 Definimos el tipo de alerta
        Alert alert = new Alert(tipo);
        // 00191322 Titulo de nuestra ventana
        alert.setTitle(titulo);
        // 00191322 Asignamos el valor del header, en este caso null
        alert.setHeaderText(null);
        // 00191322 El mensaje de contendra nuestra ventana
        alert.setContentText(mensaje);
        // 00191322 Metodo que bloquea las demas ventanas y espera hasta que el usario interactue con el cuadro de dialogo
        alert.showAndWait();
    }

    @FXML // 00402523 elemento de FXML
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


    @FXML // 00402523 elemento de FXML // 00402523 elemento de FXML
    public void obtenerReporteA() { // 00402523 metodo para generar el reporte
        System.out.println(reporteCliente.getValue().getNombre()); // 00402523 impresion de los valores recibidos en consola
        System.out.println(reporteAFechaInicio.getValue()); // 00402523 impresion de los valores recibidos en consola
        System.out.println(reporteAFechaFin.getValue());    // 00402523 impresion de los valores recibidos en consola

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // 00402523 formato de fecha para enviar al query

        try { // 00402523 try catch para generar el reporte
            java.util.Date dateInicio = formato.parse(String.valueOf(reporteAFechaInicio.getValue())); // 00402523 conversión del dato recibido al formato de fecha
            java.sql.Date fechaInicio = new java.sql.Date(dateInicio.getTime()); // 00402523 creacion de la fecha
            java.util.Date dateFin = formato.parse(String.valueOf(reporteAFechaFin.getValue())); // 00402523 conversión del dato recibido al formato de fecha
            java.sql.Date fechaFin = new java.sql.Date(dateFin.getTime()); // 00402523 creacion de la fecha

            ReporteParametro reporteParametro = new ReporteParametro(reporteCliente.getValue().getId(), fechaInicio, fechaFin); // 00402523 instancia de los parametros del reporte
            ReporteA reporteA = new ReporteA(); // 00402523 instancia de la clase del reporte

            reporteA.generarConsulta(reporteParametro); // 00402523 se envian los parametros al metodo de la clase reporte

        } catch (ParseException e) { // 00402523 catch por si ocurriere un error
            throw new RuntimeException(e); // 00402523 se lanza el error
        }
    }

    @FXML // 00402523 elemento de FXML // 00402523 elemento de FXML // 00402523 elemento de FXML
    public void obtenerReporteB() { // 00402523 metodo para generar el reporte
        System.out.println(reporteCliente.getValue().getNombre()); // 00402523 impresion de valores recibidos en consola

        String fechaIncompleta = String.valueOf(cbReporteBanio.getValue()); // 00402523 se guarda la fecha como String
        fechaIncompleta = fechaIncompleta.substring(0,8); // 00402523 se genera una fecha con año y mes YYYY-MM
        System.out.println("fecha incompleta " + fechaIncompleta); // 00402523 se imprime la fecha en consola
        int primerDia, ultimoDia; // 00402523 variables para almacenar el primer y ultimo día del mes recibido
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");  // 00402523 formato de fecha para enviar al query
        SimpleDateFormat formatoQuery = new SimpleDateFormat("yyyy-MM-dd"); // 00402523 formato de fecha para enviar al query

        try { // 00402523 try catch para generar la fecha y el proceso del reporte
            java.util.Date dateRecibida = formato.parse(String.valueOf(cbReporteBanio.getValue())); // 00402523 se convierte la fecha recibida
            java.sql.Date fechaRecibida = new java.sql.Date(dateRecibida.getTime()); // 00402523 se almacena la fecha recibida

            Calendar calendar = Calendar.getInstance(); // 00402523 se crea una instancia de Calendar
            calendar.setTime(fechaRecibida); // 00402523 se setea Calendar con la fecha recibida

            primerDia = calendar.getActualMinimum(Calendar.DAY_OF_MONTH); // 00402523 se calcula el primer dia del mes
            ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 00402523 se calcula el ultimo dia del mes

            System.out.println(primerDia); // 00402523 se imprime en consola para verificar
            String fechaCadenaInicio = fechaRecibida + "-" + primerDia; // 00402523 se genera una nueva fecha de inicio
            java.util.Date dateInicio = formatoQuery.parse(fechaCadenaInicio); // 00402523 se guarda con formato de query
            java.sql.Date fechaInicio = new java.sql.Date(dateInicio.getTime()); // 00402523 se guarda como SQL DATE
            System.out.println("inicio " + fechaInicio); // 00402523 se imprime en consola para verificar

            System.out.println(ultimoDia); // 00402523 se imprime en consola para verificar
            String fechaCadenaFin = fechaIncompleta + ultimoDia; // 00402523 se genera una nueva fecha de fin
            System.out.println(fechaCadenaFin); // 00402523 se imprime en consola para verificar
            java.util.Date dateFin = formatoQuery.parse(fechaCadenaFin); // 00402523 se guarda con formato de query
            java.sql.Date fechaFin = new java.sql.Date(dateFin.getTime()); // 00402523 se guarda como SQL DATE
            System.out.println("fin " + fechaFin); // 00402523 se imprime en consola para verificar


            ReporteParametro reporteParametro = new ReporteParametro(reporteCliente.getValue().getId(), fechaInicio, fechaFin); // 00402523 instancia de los parametros del reporte
            ReporteB reporteB = new ReporteB(); // 00402523 instancia de la clase del reporte

            reporteB.generarConsulta(reporteParametro); // 00402523 se envian los parametros al metodo de la clase reporte

        } catch (ParseException e) { // 00402523 catch por si ocurriere un error
            throw new RuntimeException(e); // 00402523 se lanza el error
        }
    }

    @FXML // 00402523 elemento de FXML
    public void obtenerReporteC() { // 00402523 metodo para generar el reporte
        System.out.println(reporteCliente.getValue().getNombre()); // 00402523 impresion de los valores recibidos en consola




        try { // 00402523 try catch para generar el reporte


            ReporteParametro reporteParametro = new ReporteParametro(reporteCliente.getValue().getId()); // 00402523 instancia de los parametros del reporte
            ReporteC reporteC = new ReporteC(); // 00402523 instancia de la clase del reporte

            reporteC.generarConsulta(reporteParametro); // 00402523 se envian los parametros al metodo de la clase reporte

        } catch (Exception e) { // 00402523 catch por si ocurriere un error
            throw new RuntimeException(e); // 00402523 se lanza el error
        }
    }

    @FXML // 00402523 elemento de FXML
    public void obtenerReporteD() { // 00402523 metodo para generar el reporte
        try { // 00402523 try catch para generar el reporte


            ReporteParametro reporteParametro = new ReporteParametro(cbReporteDfacilitador.getValue().getId(), cbReporteDfacilitador.getValue().getTipo()); // 00402523 instancia de los parametros del reporte
            ReporteD reporteD = new ReporteD(); // 00402523 instancia de la clase del reporte

            reporteD.generarConsulta(reporteParametro); // 00402523 se envian los parametros al metodo de la clase reporte

        } catch (Exception e) { // 00402523 catch por si ocurriere un error
            throw new RuntimeException(e); // 00402523 se lanza el error
        }
    }

    private void cargarComboClientes() { // 00191322 metodo para cargar el combobox de los clientes
        try { // 00402523 try catch para la query
            String query = "SELECT * FROM cliente"; // 00191322 query sql
            Statement stmt = DatabaseConnection.getConnection().createStatement(); // 00191322 se crea Statement
            ResultSet rs = stmt.executeQuery(query); // 00191322 se ejecuta statement y se guarda en Resultset

            listadoClientesCombo = new ArrayList<Cliente>(); // 00191322 se inicializa listadoClientesCombo

            while (rs.next()) { // 00191322 se ejecuta mientras hayan datos
                Cliente cliente = new Cliente(rs.getInt("id"), // 00191322 instancia de cliente, se asigna el id por constructor
                        rs.getString("nombre"), // 00191322 se asigna el nombre por constructor
                        rs.getString("direccion"), // 00191322 se asigna la direccion por constructor
                        rs.getString("telefono")); // 00191322 se asigna el telefono por constructor

                reporteCliente.getItems().add(cliente); // 00191322 se agrega el cliente al combobox
            }
        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            System.out.println(e.getMessage()); // 00191322 Se imprime el mensaje en consola
            throw new RuntimeException(e); // 00191322 Se lanza la excepcion
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
    }

    private void cargarComboFacilitadores() { // 00191322 metodo para cargar el combobox de los facilitadores
        try { // 00402523 try catch para la query
            String query = "SELECT * FROM facilitador";  // 00191322 query sql
            Statement stmt = DatabaseConnection.getConnection().createStatement(); // 00191322 se crea Statement
            ResultSet rs = stmt.executeQuery(query); // 00191322 se ejecuta statement y se guarda en Resultset

//            listadoClientesCombo = new ArrayList<Cliente>();

            while (rs.next()) { // 00191322 se ejecuta mientras hayan datos
                Facilitador facilitador = new Facilitador(rs.getInt("id"), // 00191322 se crea una nueva instancia de Facilitador por constructor y se setea el Id
                        rs.getString("tipo")); // 00191322 se asigna el tipo por constructor

                cbReporteDfacilitador.getItems().add(facilitador); // 00191322 se agrega el facilitador al combobox
            }
        } catch (SQLException e) { // 00191322 Se catchea la excepcion SQLException
            System.out.println(e.getMessage()); // 00191322 Se imprime el mensaje en consola
            throw new RuntimeException(e); // 00191322 Se lanza la excepcion
        } finally { // 00191322 Cuando se termina el bloque try-catch se ejecutara esto
            DatabaseConnection.closeConnection(); // 00191322 Se llama a la Clase DatabaseConection y se cierra la conexion con el metodo estatico closeConnection()
        }
    }

    private LocalDate stringALocalDate(String fechaString) { // 00191322 metodo para convertir fecha
        return LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 00191322 se devuelve la fecha
    }
}