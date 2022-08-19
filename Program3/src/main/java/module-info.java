module com.example.program3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.program3 to javafx.fxml;
    exports com.example.program3;
}