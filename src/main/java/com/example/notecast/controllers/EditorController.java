package com.example.notecast.controllers;

import com.example.notecast.models.dictionary.SearchResult;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    private Button dictionaryButton, googleButton, hideButton;
    @FXML
    private SplitPane leftRightSplitPane, topBottomSplitPane;
    @FXML
    private AnchorPane leftPane, rightPane, rightTopPane, rightBottomPane;
    @FXML
    private TextField wordSearchInput, googleSearchInput;
    @FXML
    private ListView<String> listView;
    @FXML
    void showDictionarySearchResults() {
        listView.getItems().clear();
        String word = wordSearchInput.getText();
        System.out.println(word);
        try {
            new SearchResult(word, listView);
        } catch (InterruptedException | IOException | UnirestException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showGoogleSearchResults() {
        String phrase = googleSearchInput.getText();
        System.out.println(phrase);
        // TODO: implement API call
    }

    @FXML
    void hideRightPane() {
        rightPane.setMinWidth(0.0);
        rightPane.setMaxWidth(0.0);
        leftRightSplitPane.setDividerPosition(0, 1);
        hideButton.setVisible(false);
        dictionaryButton.setVisible(true);
        googleButton.setVisible(true);
        rightPane.setVisible(false);
    }

    @FXML
    void showDictionary() {
        rightPane.setVisible(true);
        rightTopPane.setVisible(true);
        rightBottomPane.setVisible(false);

        rightPane.setMinWidth(200.0);
        rightPane.setMaxWidth(Double.POSITIVE_INFINITY);
        rightBottomPane.setMaxHeight(0.0);
        rightTopPane.setMaxHeight(Double.POSITIVE_INFINITY);

        leftRightSplitPane.setDividerPosition(0, 0.6);
        topBottomSplitPane.setDividerPosition(0, 1);

        dictionaryButton.setVisible(false);
        googleButton.setVisible(true);
        hideButton.setVisible(true);
    }

    @FXML
    void showGoogleSearch() {
        rightPane.setVisible(true);
        rightBottomPane.setVisible(true);
        rightTopPane.setVisible(false);

        rightPane.setMinWidth(200.0);
        rightPane.setMaxWidth(Double.POSITIVE_INFINITY);
        rightBottomPane.setMaxHeight(Double.POSITIVE_INFINITY);
        rightTopPane.setMaxHeight(0);

        leftRightSplitPane.setDividerPosition(0, 0.6);
        topBottomSplitPane.setDividerPosition(0, 0);

        googleButton.setVisible(false);
        dictionaryButton.setVisible(true);
        hideButton.setVisible(true);
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
            assert fileReader != null;
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
