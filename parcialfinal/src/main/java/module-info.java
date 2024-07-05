module org.example.parcialfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.parcialfinal to javafx.fxml;
    exports org.example.parcialfinal;
}