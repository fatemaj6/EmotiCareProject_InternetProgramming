package com.example.emoticare.learning.model;
import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long resourceId;
    private String title;
    private String description;
    private String resourceType;
    private String category;
    private String content;
    private int duration;
    private int progress;
    private boolean completed;
    private Date createdAt;

    public Resource() {}
    public Resource(String title, String description, String resourceType, String category) {
        this.title = title;
        this.description = description;
        this.resourceType = resourceType;
        this.category = category;
        this.progress = 0;
        this.completed = false;
        this.createdAt = new Date();
    }

    public Long getResourceId() { return resourceId; }
    public void setResourceId(Long resourceId) { this.resourceId = resourceId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}