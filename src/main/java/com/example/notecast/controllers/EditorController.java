package com.example.notecast.controllers;

import com.example.notecast.models.dictionary.SearchResult;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

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
            new SearchResult(word, listView);
        } catch (InterruptedException | IOException | UnirestException | ExecutionException e) {
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
