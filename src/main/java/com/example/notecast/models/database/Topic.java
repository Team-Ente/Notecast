package com.example.notecast.models.database;

import java.sql.Timestamp;

public class Topic {
    final String id;
    final String title;
    final Timestamp timeCreated;
    final Timestamp lastEdited;
    final String notebookId;
    final Content content;

    public Topic(String id, String title, Timestamp timeCreated, Timestamp lastEdited, String notebookId, Content content) {
        this.id = id;
        this.title = title;
        this.timeCreated = timeCreated;
        this.lastEdited = lastEdited;
        this.notebookId = notebookId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public Timestamp getLastEdited() {
        return lastEdited;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public Content getContent() {
        return content;
    }
}
