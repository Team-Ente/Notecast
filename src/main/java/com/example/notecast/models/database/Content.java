package com.example.notecast.models.database;

public class Content {
    final int id;
    final String baseHTML;
    final String baseStyles;
    final String baseJS;
    final String rootLocation;

    public Content(int id, String baseHTML, String baseStyles, String baseJS, String rootLocation) {
        this.id = id;
        this.baseHTML = baseHTML;
        this.baseStyles = baseStyles;
        this.baseJS = baseJS;
        this.rootLocation = rootLocation;
    }
}
