module com.example.notecast {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.google.gson;
    requires java.sql;
    requires json;
    requires java.net.http;
    requires unirest.java;
    requires jdk.xml.dom;
    requires java.desktop;

    opens com.example.notecast to javafx.fxml;
    exports com.example.notecast;
    exports com.example.notecast.controllers;
    exports com.example.notecast.models.database;
    opens com.example.notecast.controllers to javafx.fxml;
}