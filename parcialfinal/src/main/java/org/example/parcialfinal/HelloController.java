package org.example.parcialfinal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.parcialfinal.modelo.Facilitador;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

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