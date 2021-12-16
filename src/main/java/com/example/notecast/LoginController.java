package com.example.notecast;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private ImageView notecastImageview;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label Error;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField userpasswordField;

    public void cancelbuttonAction(ActionEvent e)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    public void loginButtonAction(ActionEvent e)
    {
        if(usernameTextField.getText().isBlank() || usernameTextField.getText().isBlank())
            Error.setText("Invalid Username or Password");
        else
            Error.setText("Success");
    }
}