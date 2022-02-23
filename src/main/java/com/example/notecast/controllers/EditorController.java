package com.example.notecast.controllers;

import com.example.notecast.models.dictionary.Definition;
import com.example.notecast.models.dictionary.Meaning;
import com.example.notecast.models.dictionary.SearchResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.HTMLEditor;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class EditorController {
    @FXML
    private HTMLEditor htmlEditor;
//    @FXML
//    private Button htmltoText;
//    @FXML
//    private Button webbutton;
//    @FXML
//    private TextArea textarea;
//    @FXML
//    private WebView webview;

    @FXML
    private TextField searchInput;
    @FXML
    private ListView<String> listView;
    @FXML
    void showResults() {
        listView.getItems().clear();
        String word = searchInput.getText();
        System.out.println(word);
        try {
            SearchResult result = new SearchResult(word);
            for (Meaning mean : result.getMeanings()) {
                for (Definition def : mean.definitions)
                    listView.getItems().add(def.definitionText);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

//    public void handleHTMLtoText(ActionEvent event) throws IOException {
//        textarea.setText(htmlEditor.getHtmlText());
//        PrintWriter writer = new PrintWriter("text.html", StandardCharsets.UTF_8);
//        writer.println(htmlEditor.getHtmlText());
//        writer.close();
//    }
//    public void handleHTMLtoWeb(ActionEvent event) throws FileNotFoundException {
//        WebEngine webengine = webview.getEngine();
//        StringBuilder html = new StringBuilder();
//        FileReader fileReader = new FileReader("text.html");
////        String result;
//        try {
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line;
//            while((line=bufferedReader.readLine())!= null){
//                html.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        webengine.loadContent(html.toString(), "text/html");
//    }

    @FXML
    public void initialize() {
        StringBuilder html = new StringBuilder();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("text.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line=bufferedReader.readLine())!= null){
                html.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        htmlEditor.setHtmlText(html.toString());
    }

    public void exit() throws IOException {
        PrintWriter writer = new PrintWriter("text.html", StandardCharsets.UTF_8);
        writer.println(htmlEditor.getHtmlText());
        writer.close();
    }
}
