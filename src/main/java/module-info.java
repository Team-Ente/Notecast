module com.example.notecast {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.notecast to javafx.fxml;
    exports com.example.notecast;
}