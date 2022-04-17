package com.example.notecast.models.database;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Notebook {
    final String id;
    final String title;
    final int priority;
    final Timestamp timeCreated;
    final Timestamp lastEdited;
    final String userEmail;
    final ArrayList<Topic> topics;

    public Notebook(String id, String title, int priority, Timestamp timeCreated, Timestamp lastEdited, String userEmail, ArrayList<Topic> topics) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.timeCreated = timeCreated;
        this.lastEdited = lastEdited;
        this.userEmail = userEmail;
        this.topics = topics;
    }

    public void addTopic(Topic topic)
    {
        topics.add(topic);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public Timestamp getLastEdited() {
        return lastEdited;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

}
