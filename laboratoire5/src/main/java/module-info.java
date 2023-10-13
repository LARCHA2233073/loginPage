module com.example.laboratoire5 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.laboratoire5 to javafx.fxml;
    exports com.example.laboratoire5;
}