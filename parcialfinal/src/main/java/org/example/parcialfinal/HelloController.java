package org.example.parcialfinal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.parcialfinal.modelo.Facilitador;

import java.net.URL;
import java.sql.*;
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
    private TableView<?> tblClientes;

    @FXML
    private TableView<?> tblCompras;

    @FXML
    private TableView<?> tblTarjetas;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private TextArea txtAreaDireccion;

    @FXML
    private TextField txtBuscarCliente;

    @FXML
    private TextField txtBuscarCompra;

    @FXML
    private TextField txtBuscarTarjeta;

    @FXML
    private TextField txtClienteApellido;

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

    public void conexionPrueba() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pooparcialfinal", "parcial", "1234");
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM facilitador";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Facilitador facilitador = new Facilitador(rs.getInt("id"), rs.getString("tipo"));

                System.out.println(facilitador.getId()+"\t"+facilitador.getTipo());
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conexionPrueba();
    }
}