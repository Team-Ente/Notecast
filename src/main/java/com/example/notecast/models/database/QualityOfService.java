package com.example.notecast.models.database;

public class QualityOfService {
    final String id;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    final String type;
    // TODO: add more attributes later

    public QualityOfService(String id, String type) {
        this.id = id;
        this.type = type;
    }
}
