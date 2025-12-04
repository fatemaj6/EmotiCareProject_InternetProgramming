package com.example.emoticare.learning.service;
import com.example.emoticare.learning.model.Resource;
import com.example.emoticare.learning.repo.ResourceRepository;
import java.util.List;

public class LearningHubService {
    private ResourceRepository resourceRepository;

    public LearningHubService() { this.resourceRepository = new ResourceRepository(); }

    public List<Resource> getAllResources() { return resourceRepository.getAllResources(); }
    public List<Resource> getResourcesByCategory(String category) { return resourceRepository.getResourcesByCategory(category); }
    public Resource getResourceById(Long resourceId) { return resourceRepository.getResourceById(resourceId); }

    public int getCompletionPercentage(List<Resource> resources) {
        if (resources.isEmpty()) return 0;
        long completedCount = resources.stream().filter(Resource::isCompleted).count();
        return (int) ((completedCount * 100) / resources.size());
    }
}