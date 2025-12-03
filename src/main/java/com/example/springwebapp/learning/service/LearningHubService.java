package com.example.springwebapp.learning.service;

import com.example.springwebapp.learning.model.Resource;
import com.example.springwebapp.learning.repo.ResourceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LearningHubService {
    private final ResourceRepository repo;

    public LearningHubService(ResourceRepository repo) {
        this.repo = repo;
        this.repo.seedDemo();
    }

    // UC005: View Awareness Resources
    public List<Resource> getAllResources() {
        return repo.findAll();
    }

    // UC006: Take Mental Health Quiz (simplified)
    public Optional<Resource> getQuizById(String id) {
        return repo.findById(id).filter(r -> "QUIZ".equals(r.getType()));
    }

    // UC007: Track Progress & Badges (simplified demo)
    public String getProgressSummary(String userId) {
        return "Progress: 3/12 modules completed, 2 badges earned.";
    }

    // UC008: Manage Learning Resources (Admin)
    public Resource addResource(String title, String type, String content, int duration) {
        Resource r = new Resource(UUID.randomUUID().toString(), title, type, content, duration);
        return repo.save(r);
    }

    public void deleteResource(String id) {
        repo.delete(id);
    }
}

