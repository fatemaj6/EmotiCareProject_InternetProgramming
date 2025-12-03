package com.example.springwebapp.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ForumPost {
    private String id;
    private String userId;
    private String authorName;
    private String title;
    private String content;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ForumComment> comments;
    private boolean isPinned;

    public ForumPost() {
        this.comments = new ArrayList<>();
    }

    public ForumPost(String id, String userId, String authorName, String title, 
                     String content, String category, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.authorName = authorName;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.comments = new ArrayList<>();
        this.isPinned = false;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public List<ForumComment> getComments() { return comments; }
    public void setComments(List<ForumComment> comments) { this.comments = comments; }
    public boolean isPinned() { return isPinned; }
    public void setPinned(boolean pinned) { isPinned = pinned; }
}
