package org.example.parcialfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    //// 00084020 declaracion clase HelloApplication que extiende Application
    @Override
    public void start(Stage stage) throws IOException {
        // 00084020 sobeescritura del metodo start
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vista-principal.fxml"));
        // 00084020 carga el archivo FXML para la interfaz
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        // 00084020 crea una escena con las dimensiones indicadas
        stage.setTitle("El Banco Central de Nlogonia !");
        // 00084020 establece el titulo
        stage.setScene(scene);
        //00084020 asigna el  la escena a la ventana
        stage.show();
        //00084020 muestra la ventana

    }

    public static void main(String[] args) {
        launch();
    }
    //00084020 corre la aplicacion
}