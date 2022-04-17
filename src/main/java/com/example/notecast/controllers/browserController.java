package com.example.notecast.controllers;

import com.example.notecast.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javafx.scene.control.Button;


import java.awt.*;
import java.io.IOException;

public class browserController {
    @FXML
    private Button newNote;
    @FXML
    private Button browseNotes;
    @FXML
    private Button settings;
    @FXML
    private Button instructions;
    @FXML
    private Button exit;
    public void newNoteAction(ActionEvent e) throws IOException {
        System.out.println("New Note Created");
                FXMLLoader loader = new FXMLLoader(App.class.getResource("editor.fxml"));
                Parent root = loader.load();
                EditorController controller = loader.getController();
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(windowEvent -> {
                    windowEvent.consume();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exit");
                    alert.setHeaderText("Save and Exit?");
                    if(alert.showAndWait().get() == ButtonType.OK) {
                        try {
                            controller.exit();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        stage.close();
                    }
                });
    }
    public void browseNoteAction(ActionEvent e) throws IOException {
        System.out.println("Browsing Notebooks");
        //Sami do your magic
    }
    public void settingsAction(ActionEvent e) throws IOException {
        System.out.println("Settings opened");
        //heheheeee
    }
    public void instructionsAction(ActionEvent e) throws IOException {
        System.out.println("Instructions Showed");
        //who
    }
    public void exitAction(ActionEvent e)
    {
        Stage stage = (Stage)exit.getScene().getWindow();
        stage.close();
    }


}
