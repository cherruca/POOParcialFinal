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
    private TextField txtTarjetaId;

    @FXML
    private TextField txtCompraId;

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
        this(new ClienteControlador());
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
    protected void buscarCliente() {
        String termino = txtBuscarCliente.getText();
        if(verificarCamposConDatos("cliente", "buscar")) {
            List<Cliente> clientes = clienteControlador.buscarClientes(termino);
            observableListCliente.clear();
            observableListCliente.addAll(clientes);
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

    private void limpiarCampos(String modelo) {
        if(Objects.equals(modelo, "cliente")) {
            txtClienteId.clear();
            txtClienteNombre.clear();
            txtClienteTelefono.clear();
            txtAreaDireccion.clear();
            txtAreaDescripcion.clear();
        } else if (Objects.equals(modelo, "tarjeta")) {
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
        }
        return true;
    }
}