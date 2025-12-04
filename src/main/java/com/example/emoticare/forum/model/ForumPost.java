package com.example.emoticare.forum.model;
import java.io.Serializable;
import java.util.Date;

public class ForumPost implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long forumId;
    private Long userId;
    private String title;
    private String content;
    private String category;
    private int likes;
    private Date createdAt;

    public ForumPost() {}
    public ForumPost(Long userId, String title, String content, String category) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.likes = 0;
        this.createdAt = new Date();
    }

    public Long getForumId() { return forumId; }
    public void setForumId(Long forumId) { this.forumId = forumId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}