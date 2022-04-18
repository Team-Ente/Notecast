package com.example.notecast.controllers;

import com.example.notecast.models.database.Notebook;
import com.example.notecast.models.database.Topic;
import com.example.notecast.models.database.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.event.ActionEvent;
import java.util.Stack;

public class NotebookListController {

    Stack<Scene> stateStack;
    public void setStateStack(Stack<Scene> stStack){ stateStack = stStack;}
    User user;
    public void setUser(User u){ user = u;}

    private final Node rootIcon =
            new ImageView(new Image(getClass().getResourceAsStream("/image/root.png")));

    TreeItem<String> rootNode =
            new TreeItem<>( user.getName() + "\'s Notebooks", rootIcon);

    @FXML
    TreeView<String> notesTreeView;

    @FXML
    public void backAction(ActionEvent e) {

    }

    @FXML
    void initialize() {
        rootNode.setExpanded(true);
        for (Notebook notebook : user.getNotebooks()) {
            TreeItem<String> nbNode = new TreeItem<>(notebook.getTitle());
            for(Topic topic : notebook.getTopics()) {
                TreeItem<String> tLeaf = new TreeItem<>(topic.getTitle());
                nbNode.getChildren().add(tLeaf);
            }
            rootNode.getChildren().add(nbNode);
        }

        notesTreeView.setEditable(true);
//        notesTreeView.setCellFactory(p -> new );
    }


}
