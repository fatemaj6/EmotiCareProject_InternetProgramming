package com.example.springwebapp.learning.repo;

import com.example.springwebapp.learning.model.Resource;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceRepository {
    private final Map<String, Resource> resources = new ConcurrentHashMap<>();

    public List<Resource> findAll() {
        return new ArrayList<>(resources.values());
    }

    public Optional<Resource> findById(String id) {
        return Optional.ofNullable(resources.get(id));
    }

    public Resource save(Resource resource) {
        resources.put(resource.getId(), resource);
        return resource;
    }

    public void delete(String id) {
        resources.remove(id);
    }

    // Seed demo resources
    public void seedDemo() {
        if (resources.isEmpty()) {
            save(new Resource(UUID.randomUUID().toString(), "Understanding Anxiety", "ARTICLE",
                    "Educational article on anxiety and stress.", 15));
            save(new Resource(UUID.randomUUID().toString(), "Mindfulness for Students", "VIDEO",
                    "Video lesson on mindfulness practices.", 30));
            save(new Resource(UUID.randomUUID().toString(), "Stress Management Quiz", "QUIZ",
                    "10 questions on stress management.", 10));
        }
    }
}
