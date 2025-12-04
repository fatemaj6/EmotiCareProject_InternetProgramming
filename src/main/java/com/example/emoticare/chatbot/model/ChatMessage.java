package com.example.emoticare.chatbot.model;
import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long messageId;
    private Long userId;
    private String message;
    private String response;
    private Date createdAt;

    public ChatMessage() {}
    public ChatMessage(Long userId, String message) {
        this.userId = userId;
        this.message = message;
        this.createdAt = new Date();
    }

    public Long getMessageId() { return messageId; }
    public void setMessageId(Long messageId) { this.messageId = messageId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}