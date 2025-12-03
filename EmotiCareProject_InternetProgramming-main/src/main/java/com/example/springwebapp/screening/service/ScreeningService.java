package com.example.springwebapp.screening.service;

import com.example.springwebapp.screening.model.Screening;
import com.example.springwebapp.screening.repo.ScreeningRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScreeningService {
    private final ScreeningRepository repository;

    public ScreeningService(ScreeningRepository repository) {
        this.repository = repository;
    }

    public Screening completeScreening(String userId, String screeningType, int totalScore) {
        String id = UUID.randomUUID().toString();
        String severity = calculateSeverity(screeningType, totalScore);
        String recommendations = generateRecommendations(severity);

        Screening screening = new Screening(
            id, userId, screeningType, totalScore, severity, 
            LocalDateTime.now(), recommendations
        );

        return repository.save(screening);
    }

    public List<Screening> getUserScreenings(String userId) {
        return repository.findByUserId(userId);
    }

    public Optional<Screening> getScreeningById(String id) {
        return repository.findById(id);
    }

    private String calculateSeverity(String type, int score) {
        if (score <= 4) return "MINIMAL";
        else if (score <= 9) return "MILD";
        else if (score <= 14) return "MODERATE";
        else return "SEVERE";
    }

    private String generateRecommendations(String severity) {
        switch (severity) {
            case "MINIMAL":
                return "Your results indicate minimal symptoms. Continue practicing self-care and healthy habits.";
            case "MILD":
                return "Consider stress management techniques and lifestyle adjustments. Review our learning resources.";
            case "MODERATE":
                return "We recommend consulting with a mental health professional and exploring our support resources.";
            case "SEVERE":
                return "Please seek professional help immediately. Contact a mental health professional or crisis helpline.";
            default:
                return "Please consult with a healthcare provider for personalized advice.";
        }
    }
}
