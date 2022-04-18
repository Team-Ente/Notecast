package com.example.notecast;

import com.example.notecast.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Stack<Scene> stateStack = new Stack<>();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stateStack.push(scene);
            controller.setStateStack(stateStack);
            stage.getIcons().add(new Image("D:\\NoteCast\\src\\main\\java\\com\\example\\notecast\\icon.png"));
            stage.setTitle("NoteCast");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}