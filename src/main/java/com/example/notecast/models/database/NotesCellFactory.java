package com.example.notecast.models.database;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NotesCellFactory extends TreeCell<String> {
    private TextField textField;
    private ContextMenu addMenu = new ContextMenu();

    public NotesCellFactory() {
        MenuItem addMenuItem = new MenuItem("Add Topic");
        addMenu.getItems().add(addMenuItem);
        addMenuItem.setOnAction((EventHandler) t -> {
            TreeItem newTopic =
                    new TreeItem<>("New Topic");
            getTreeItem().getChildren().add(newTopic);
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                if (
                        !getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
                ){
                    setContextMenu(addMenu);
                }
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(textField.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });

    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
