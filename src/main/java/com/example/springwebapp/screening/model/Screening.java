package com.example.springwebapp.screening.model;

import java.time.LocalDateTime;

public class Screening {
    private String id;
    private String userId;
    private String screeningType;
    private int totalScore;
    private String severity;
    private LocalDateTime completedAt;
    private String recommendations;

    public Screening() {}

    public Screening(String id, String userId, String screeningType, int totalScore, 
                     String severity, LocalDateTime completedAt, String recommendations) {
        this.id = id;
        this.userId = userId;
        this.screeningType = screeningType;
        this.totalScore = totalScore;
        this.severity = severity;
        this.completedAt = completedAt;
        this.recommendations = recommendations;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getScreeningType() { return screeningType; }
    public void setScreeningType(String screeningType) { this.screeningType = screeningType; }
    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
}
