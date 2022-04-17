package com.example.notecast.controllers;

import com.example.notecast.utils.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.notecast.utils.DatabaseHandler.signup;

public class RegisterController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField professionTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmpasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button cancelButton;
    public void registerButtonAction(ActionEvent e) throws IOException {
        System.out.println(usernameTextField.getText());
        System.out.println(emailTextField.getText());
        System.out.println(professionTextField.getText());
        System.out.println(passwordField.getText());
        System.out.println(confirmpasswordField.getText());
        var user = signup(usernameTextField.getText(),emailTextField.getText(), professionTextField.getText(),  passwordField.getText());
        System.out.println(user);
    }
    public void cancelbuttonAction(ActionEvent e)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

}
