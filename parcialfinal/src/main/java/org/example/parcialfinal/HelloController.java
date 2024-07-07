package org.example.parcialfinal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import org.example.parcialfinal.controllador.ClienteControlador;
import org.example.parcialfinal.controllador.DatabaseConnection;
import org.example.parcialfinal.modelo.Cliente;
import org.example.parcialfinal.modelo.Facilitador;

import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button btnBuscarCliente;

    @FXML
    private Button btnBuscarCompra;

    @FXML
    private Button btnBuscarTarjeta;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private Button btnEliminarCompra;

    @FXML
    private Button btnEliminarTarjeta;

    @FXML
    private Button btnGuardarCliente;

    @FXML
    private Button btnGuardarCompra;

    @FXML
    private Button btnGuardarTarjeta;

    @FXML
    private ComboBox<?> cbClientes;

    @FXML
    private ComboBox<?> cbFacilitador;

    @FXML
    private ComboBox<?> cbTarjetas;

    @FXML
    private ComboBox<?> cbTipoTarjeta;

    @FXML
    private DatePicker datePickerFechaCompra;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableView<?> tblCompras;

    @FXML
    private TableView<?> tblTarjetas;

    @FXML
    private TableColumn<Cliente, String> tcClienteDireccion;

    @FXML
    private TableColumn<Cliente, Integer> tcClienteId;

    @FXML
    private TableColumn<Cliente, String> tcClienteNombre;

    @FXML
    private TableColumn<Cliente, String> tcClienteTelefono;

    @FXML
    private TableColumn<?, ?> tcCompraDescripcion;

    @FXML
    private TableColumn<?, ?> tcCompraFechaCompra;

    @FXML
    private TableColumn<?, ?> tcCompraId;

    @FXML
    private TableColumn<?, ?> tcCompraMonto;

    @FXML
    private TableColumn<?, ?> tcCompraTarjeta;

    @FXML
    private TableColumn<?, ?> tcTarjetaCvc;

    @FXML
    private TableColumn<?, ?> tcTarjetaFechaVen;

    @FXML
    private TableColumn<?, ?> tcTarjetaId;

    @FXML
    private TableColumn<?, ?> tcTarjetaNomCliente;

    @FXML
    private TableColumn<?, ?> tcTarjetaNum;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private TextArea txtAreaDireccion;

    @FXML
    private TextField txtClienteId;

    @FXML
    private TextField txtBuscarCliente;

    @FXML
    private TextField txtBuscarCompra;

    @FXML
    private TextField txtBuscarTarjeta;

    @FXML
    private TextField txtClienteNombre;

    @FXML
    private TextField txtClienteTelefono;

    @FXML
    private TextField txtCvc;

    @FXML
    private TextField txtFechaVencimiento;

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtNumTarjeta;

    @FXML
    private VBox vbMenu;

    ObservableList<Cliente> observableListCliente = FXCollections.observableArrayList();

    /**
     * Se declaran los controladores como atributos y se
     * hace una simulacion de Dependency Injection (Inyeccion de Dependencias)
     */
    private ClienteControlador clienteControlador;


    // Constructor sin argumentos requerido por FXM Loader
    public HelloController() {
        this.clienteControlador = new ClienteControlador();
    }

    /**
     * Se inicializan las instancias de los controladores
     * por medio de un constructor de la clase
     * @param clienteControlador instancia de ClienteControlador a ser inyectada
     */
    public HelloController(ClienteControlador clienteControlador) {
        this.clienteControlador = clienteControlador;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        /**
         * Se asigna datos a la tabla por medio de un ObservableList
         */
        tblClientes.setItems(observableListCliente);

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

        obtenerClientes();
    }


    public void obtenerClientes() {
        // Se obtiene los registros de los clientes de la base de datos
        List<Cliente> clientes = clienteControlador.obtenerClientes();
        // Se limpia el ObservableList
        observableListCliente.clear();
        // Se añade la lista al ObservableList
        observableListCliente.addAll(clientes);
    }

    @FXML
    protected void agregarCliente() {
        Cliente cliente = new Cliente();

        if (Objects.equals(txtClienteId.getText(), "")) {
            cliente.setId(null);
        } else {
            cliente.setId(Integer.valueOf(txtClienteId.getText()));
        }

        cliente.setNombre(txtClienteNombre.getText());
        cliente.setTelefono(txtClienteTelefono.getText());
        cliente.setDireccion(txtAreaDireccion.getText());

        if(clienteControlador.persistirCliente(cliente)) {
            System.out.println("cliente agregado");
            limpiarCampos("cliente");
            obtenerClientes();
        } else {
            System.out.println("cliente no agregado");
        }
    }

    @FXML
    protected void eliminarCliente() {
        if(!Objects.equals(txtClienteId.getText(), "")){
            if(clienteControlador.eliminarCliente(Integer.parseInt(txtClienteId.getText()))) {
                System.out.println("eliminado");
                limpiarCampos("cliente");
                obtenerClientes();
            } else {
                System.out.println("hubo un error al eliminar el registro");
            }
        } else {
            System.out.println("seleccione un registro a eliminar");
        }
    }

    private void limpiarCampos(String modelo) {
        if(Objects.equals(modelo, "cliente")) {
            txtClienteId.clear();
            txtClienteNombre.clear();
            txtClienteTelefono.clear();
            txtAreaDireccion.clear();
            txtAreaDescripcion.clear();
        }
    }
}