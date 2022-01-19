package com.example.notecast.models;

import com.example.notecast.utils.NetworkHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class SearchResult {
    private String phonetic;
    private String origin;
    private String word;
    private ArrayList<Meaning> meanings;

    public String getPhonetic() {
        return phonetic;
    }

    public String getOrigin() {
        return origin;
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Meaning> getMeanings() {
        return meanings;
    }

    public void showResults(){
        System.out.println(word);
        System.out.println(phonetic);
        System.out.println(origin);
        for(Meaning meaning : meanings){
            System.out.println("===========");
            System.out.println("Parts of Speech: " + meaning.partOfSpeech);
            for (Definition def : meaning.definitions) {
                System.out.println("-------------");
                System.out.println(def.definitionText);
                System.out.println(def.example);
            }
        }
    }

    private void getResults(String word) throws IOException, InterruptedException {
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/"+word;
//        HttpResponse<JsonNode> response = NetworkHandler.getUnirestResponse(url);
//        getResultFromJSONObject((JSONObject) response.getBody().getArray().get(0));

        var response = NetworkHandler.getHttpRequestResponse(url);
        int len = response.body().length();
        getResultFromJSONObject(new JSONObject(response.body().substring(1, len-1)));
    }

    public SearchResult(String word) throws IOException, InterruptedException {
        getResults(word);
    }

    private void getResultFromJSONObject(JSONObject json) {
        ArrayList<Meaning> meaningList = new ArrayList<>();
        JSONArray means = json.optJSONArray("meanings");
        if(means == null) throw new RuntimeException(json.getString("title"));
        for(int i=0; i<means.length(); i++) meaningList.add(Meaning.createMeaningFromJSONObject(means.getJSONObject(i)));

        this.phonetic = json.optString("phonetic", "");
        this.origin = json.optString("origin", "");
        this.word = json.getString("word");
        this.meanings = meaningList;
    }
}
