package com.example.notecast.controllers;

import com.example.notecast.App;
import com.example.notecast.models.database.QualityOfService;
import com.example.notecast.models.database.User;
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
import org.w3c.dom.Text;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;
import java.util.Scanner;

public class browserController {
    Stack<Scene> stateStack;
    public void setStateStack(Stack<Scene> stStack){ stateStack = stStack;}
    User user;
    public void setUser(User u){ user = u;}
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
                FXMLLoader loader = new FXMLLoader(App.class.getResource("notebookdata.fxml"));
                Parent root = loader.load();
                notebookdataController controller = loader.getController();
                controller.setStateStack(stateStack);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    public void browseNoteAction(ActionEvent e) throws IOException {
        System.out.println("Browsing Notebooks");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("notebook_list.fxml"));
        Parent root = loader.load();
        NotebookListController controller = loader.getController();
        User user = null;
        controller.setUser(user);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stateStack.push(scene);
        stage.show();
    }
    public void settingsAction(ActionEvent e) throws IOException {
        System.out.println("Settings opened");
        //heheheeee
    }
    public void instructionsAction(ActionEvent e) throws IOException {
        System.out.println("Instructions Showed");
        //who
    }
    public void logoutAction()
    {
        Stage stage = (Stage)exit.getScene().getWindow();
        stateStack.pop();
        stage.setScene(stateStack.peek());
        stage.setOnCloseRequest(null);
    }


}
