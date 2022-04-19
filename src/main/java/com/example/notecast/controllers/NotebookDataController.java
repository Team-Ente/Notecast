package com.example.notecast.controllers;

import com.example.notecast.App;
import com.example.notecast.models.database.User;
import com.example.notecast.utils.StateManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class NotebookDataController {

    User user;
    public void setUser(User u){ user = u;}

    @FXML
    private TextField notebooktitle;
    @FXML
    private TextField topicTitle;
    @FXML
    private TextField notebookPriority;

    public void createNotebookAction(ActionEvent e) throws IOException {
        System.out.println(notebooktitle.getText());
        System.out.println(topicTitle.getText());
        System.out.println(Integer.parseInt(notebookPriority.getText()));


        System.out.println("New Note Created");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("editor.fxml"));
        Parent root = loader.load();
        EditorController controller = loader.getController();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        StateManager.push(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setTitle("Exit");
            alert.setHeaderText("Do you want save the document?");
            ButtonType buttonType = alert.showAndWait().get();
            if (ButtonType.YES.equals(buttonType)) {
                try {
                    File savedFile = controller.exit(e);


                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                StateManager.pop();
                stage.setScene(StateManager.peek());
            } else if (ButtonType.NO.equals(buttonType)) {

                StateManager.pop();
                stage.setScene(StateManager.peek());
            }
            stage.setOnCloseRequest(null);
        });
    }
}
