package com.example.notecast.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Stack;

public class settingsController {
    @FXML
    public Button returnbtn;
    @FXML
    ChoiceBox<String> fontFamily;
    @FXML
    ChoiceBox<Integer> fontSize;
    @FXML
    ChoiceBox<String> theme;

    Stack<Scene> stateStack;
    public void setStateStack(Stack<Scene> stStack){ stateStack = stStack;}
    public void returnbtnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)returnbtn.getScene().getWindow();
        stateStack.pop();
        stage.setScene(stateStack.peek());
        stage.setOnCloseRequest(null);
    }
}
