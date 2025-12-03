package com.example.springwebapp.screening.repo;

import com.example.springwebapp.screening.model.Screening;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ScreeningRepository {
    private final Map<String, Screening> screeningDb = new ConcurrentHashMap<>();

    public Screening save(Screening screening) {
        screeningDb.put(screening.getId(), screening);
        return screening;
    }

    public Optional<Screening> findById(String id) {
        return Optional.ofNullable(screeningDb.get(id));
    }

    public List<Screening> findByUserId(String userId) {
        return screeningDb.values().stream()
                .filter(s -> s.getUserId().equals(userId))
                .sorted((a, b) -> b.getCompletedAt().compareTo(a.getCompletedAt()))
                .collect(Collectors.toList());
    }

    public List<Screening> findAll() {
        return new ArrayList<>(screeningDb.values());
    }
}
