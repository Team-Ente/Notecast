module com.example.notecast {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.notecast to javafx.fxml;
    exports com.example.notecast;
}