package com.example.notecast.controllers;

import com.example.notecast.App;
import com.example.notecast.utils.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
//    @FXML
//    private ImageView notecastImageview;
//    @FXML
//    private Button loginButton;
//    @FXML
//    private Button registerButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label Error;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField userpasswordField;

    private Stage stage;
    private Scene scene;
    public void cancelbuttonAction(ActionEvent e)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    public void loginButtonAction(ActionEvent e) throws IOException
    {
        if(usernameTextField.getText().isBlank() || userpasswordField.getText().isBlank()) System.out.println("Genjam");
        else {
//            var login = DatabaseHandler.login(usernameTextField.getText(), userpasswordField.getText());
            var login = true;

            if(login) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("editor.fxml"));
                Parent root = loader.load();
                EditorController controller = loader.getController();
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
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
        }
    }
    public void registerButtonAction(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("register.fxml"));
        Parent root = loader.load();
        RegisterController controller = loader.getController();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//



    }
}