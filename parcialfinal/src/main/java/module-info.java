module org.example.parcialfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;

    opens org.example.parcialfinal to javafx.fxml;
    opens org.example.parcialfinal.modelo to javafx.base;
    opens org.example.parcialfinal.controllador to javafx.base;
    exports org.example.parcialfinal;
    exports org.example.parcialfinal.modelo;
    exports org.example.parcialfinal.controllador;
}