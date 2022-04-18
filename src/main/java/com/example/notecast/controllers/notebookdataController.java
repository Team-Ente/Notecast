package com.example.notecast.controllers;

import com.example.notecast.App;
import com.example.notecast.models.database.User;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Deque;
import java.util.Stack;

public class notebookdataController {

    Stack<Scene> stateStack;
    public void setStateStack(Stack<Scene> stStack){ stateStack = stStack;}
    @FXML
    private TextField notebooktitle;
    @FXML
    private TextField topicTitle;
    @FXML
    private TextField notebookPriority;

    public void createNotebookAction(ActionEvent e) throws IOException {
//        System.out.println(notebooktitle.getText());
//        System.out.println(topicTitle.getText());
//        System.out.println(Integer.parseInt(notebookPriority.getText()));
//        System.out.println("New Note Created");
        FXMLLoader loader = new FXMLLoader(App.class.getResource("editor.fxml"));
        Parent root = loader.load();
        EditorController controller = loader.getController();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stateStack.push(scene);
        stage.setScene(scene);
        controller.setStateStack(stateStack);
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
                    controller.exit(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                stateStack.pop();
                stage.setScene(stateStack.peek());
            } else if (ButtonType.NO.equals(buttonType)) {
                stateStack.pop();
                stage.setScene(stateStack.peek());
            }

            stage.setOnCloseRequest(null);
        });
    }
}
