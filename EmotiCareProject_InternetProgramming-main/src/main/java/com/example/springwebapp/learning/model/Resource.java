package com.example.springwebapp.learning.model;

public class Resource {
    private String id;
    private String title;
    private String type; // "ARTICLE", "VIDEO", "QUIZ"
    private String content;
    private int durationMinutes;

    public Resource() {}

    public Resource(String id, String title, String type, String content, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.content = content;
        this.durationMinutes = durationMinutes;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
}

