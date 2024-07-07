module org.example.parcialfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.parcialfinal to javafx.fxml;
    opens org.example.parcialfinal.modelo to javafx.base;
    exports org.example.parcialfinal;
}