package com.example.notecast;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.HTMLEditor;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class EditorController {
    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private Button htmltoText;
    @FXML
    private Button webbutton;
    @FXML
    private TextArea textarea;
    @FXML
    private WebView webview;

    public void handleHTMLtoText(ActionEvent event){
        textarea.setText(htmlEditor.getHtmlText());
    }
    public void handleHTMLtoWeb(ActionEvent event){
        WebEngine webengine = webview.getEngine();
        webengine.loadContent(htmlEditor.getHtmlText(), "text/html");
    }
}
