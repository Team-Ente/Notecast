package com.example.notecast.controllers;

import com.example.notecast.App;
import com.example.notecast.models.database.User;
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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class LoginController {
    Stack<Scene> stateStack;
    public void setStateStack(Stack<Scene> stStack){ stateStack = stStack;}
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
        if(usernameTextField.getText().isBlank() || userpasswordField.getText().isBlank()) System.out.println("No userbane or password given");
        else {
//            var login = DatabaseHandler.login(usernameTextField.getText(), userpasswordField.getText());
            var login = true;

            System.out.println(login);

            if(login ) {
//                String temp = login.getName();

//                try {
//                    FileWriter myWriter = new FileWriter("filename.txt");
//                    myWriter.write(temp);
//                    myWriter.close();
//                    System.out.println("Successfully wrote to the file.");
//                } catch (IOException exx) {
//                    System.out.println("An error occurred.");
//                    exx.printStackTrace();
//                }

                FXMLLoader loader = new FXMLLoader(App.class.getResource("browser.fxml"));
                Parent root = loader.load();
                browserController controller = loader.getController();
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stateStack.push(scene);
                controller.setStateStack(stateStack);
//                controller.setUser(login);
                stage.setScene(scene);
                stage.show();

                stage.setOnCloseRequest(windowEvent -> {
                    windowEvent.consume();
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
                    alert.setTitle("Logout");
                    alert.setHeaderText("Are you sure you want to logout?");
                    ButtonType buttonType = alert.showAndWait().get();
                    if (ButtonType.YES.equals(buttonType)) {
                        controller.logoutAction();
                    }
                });

//                FXMLLoader loader = new FXMLLoader(App.class.getResource("editor.fxml"));
//                Parent root = loader.load();
//                EditorController controller = loader.getController();
//                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//                stage.setOnCloseRequest(windowEvent -> {
//                    windowEvent.consume();
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Exit");
//                    alert.setHeaderText("Save and Exit?");
//                    if(alert.showAndWait().get() == ButtonType.OK) {
//                        try {
//                            controller.exit();
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        }
//                        stage.close();
//                    }
//                });
            }
        }
    }
    public void registerButtonAction(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("register.fxml"));
        Parent root = loader.load();
        RegisterController controller = loader.getController();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stateStack.push(scene);
        controller.setStateStack(stateStack);
        stage.setScene(scene);
        stage.show();
//

    }
}