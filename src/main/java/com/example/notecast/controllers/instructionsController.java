package com.example.notecast.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Stack;

public class instructionsController {
    @FXML
    public Button returnButton;

    Stack<Scene> stateStack;
    public void setStateStack(Stack<Scene> stStack){ stateStack = stStack;}

    public void returnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)returnButton.getScene().getWindow();
        stateStack.pop();
        stage.setScene(stateStack.peek());
        stage.setOnCloseRequest(null);
    }
}
