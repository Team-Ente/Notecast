package com.example.notecast.models.database;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Notebook {
    final int id;
    final String title;
    final int priority;
    final Timestamp timeCreated;
    final Timestamp lastEdited;
    final String userEmail;
    final ArrayList<Topic> topics;

    public Notebook(int id, String title, int priority, Timestamp timeCreated, Timestamp lastEdited, String userEmail, ArrayList<Topic> topics) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.timeCreated = timeCreated;
        this.lastEdited = lastEdited;
        this.userEmail = userEmail;
        this.topics = topics;
    }

}
