package com.example.notecast.models.database;

import java.sql.Timestamp;

public class Topic {
    final int id;
    final String title;
    final Timestamp timeCreated;
    final Timestamp lastEdited;
    final int notebookId;
    final Content content;

    public Topic(int id, String title, Timestamp timeCreated, Timestamp lastEdited, int notebookId, Content content) {
        this.id = id;
        this.title = title;
        this.timeCreated = timeCreated;
        this.lastEdited = lastEdited;
        this.notebookId = notebookId;
        this.content = content;
    }
}
