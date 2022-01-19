package com.example.notecast.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NetworkHandler {

//    public static HttpResponse<JsonNode> getUnirestResponse(String url) throws UnirestException, IOException {
//        HttpResponse<JsonNode> response = (HttpResponse<JsonNode>) Unirest.get(url).asJson();
//        Unirest.shutdown();
//        return response;
//    }

    public static HttpResponse<String> getHttpRequestResponse(String url) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        var client = HttpClient.newHttpClient();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
