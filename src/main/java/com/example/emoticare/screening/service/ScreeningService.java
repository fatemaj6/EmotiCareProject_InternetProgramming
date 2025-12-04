package com.example.emoticare.screening.service;
import com.example.emoticare.screening.model.Screening;
import com.example.emoticare.screening.repo.ScreeningRepository;
import java.util.List;

public class ScreeningService {
    private ScreeningRepository screeningRepository;

    public ScreeningService() { this.screeningRepository = new ScreeningRepository(); }

    public boolean submitAssessment(Long userId, String type, int score, String responses) {
        String level = calculateLevel(score);
        Screening screening = new Screening(userId, type, score, level);
        screening.setResponses(responses);
        return screeningRepository.saveScreening(screening);
    }

    public List<Screening> getUserAssessments(Long userId) {
        return screeningRepository.getUserScreenings(userId);
    }

    private String calculateLevel(int score) {
        if (score < 30) return "Normal";
        if (score < 60) return "Mild";
        if (score < 80) return "Moderate";
        return "Severe";
    }
}