module com.example.notecast {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.google.gson;
    requires java.sql;
    requires json;
    requires java.net.http;
    requires unirest.java;

    opens com.example.notecast to javafx.fxml;
    exports com.example.notecast;
    exports com.example.notecast.controllers;
    opens com.example.notecast.controllers to javafx.fxml;
}