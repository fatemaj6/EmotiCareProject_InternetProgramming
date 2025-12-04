package com.example.emoticare.screening.model;
import java.io.Serializable;
import java.util.Date;

public class Screening implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long screeningId;
    private Long userId;
    private String assessmentType;
    private int score;
    private String level;
    private String responses;
    private Date createdAt;

    public Screening() {}
    public Screening(Long userId, String assessmentType, int score, String level) {
        this.userId = userId;
        this.assessmentType = assessmentType;
        this.score = score;
        this.level = level;
        this.createdAt = new Date();
    }

    public Long getScreeningId() { return screeningId; }
    public void setScreeningId(Long screeningId) { this.screeningId = screeningId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getAssessmentType() { return assessmentType; }
    public void setAssessmentType(String assessmentType) { this.assessmentType = assessmentType; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getResponses() { return responses; }
    public void setResponses(String responses) { this.responses = responses; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}