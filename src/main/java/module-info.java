module com.example.itemlist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;
    requires java.net.http;

    opens com.example.itemlist to javafx.fxml;
    exports com.example.itemlist;
}