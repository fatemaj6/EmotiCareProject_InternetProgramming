package com.example.springwebapp.auth.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap; // WAJIB

import org.springframework.stereotype.Repository;

import com.example.springwebapp.auth.model.User;
import com.example.springwebapp.auth.service.PasswordService;

@Repository // ANOTASI INI WAJIB
public class UserRepository {

    // Simulasi database di memori
    private final ConcurrentHashMap<String, User> userDb = new ConcurrentHashMap<>();
    private final PasswordService passwordService;

    // Gunakan Constructor Injection untuk PasswordService
    public UserRepository(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

  
    public void seedAdminIfEmpty() {
        if (userDb.isEmpty()) {
            String adminId = "admin-123";
            String hash = passwordService.hash("password"); // Default password: password
            User admin = new User(adminId, "admin@example.com", hash, "ADMIN");
            admin.setFirstName("Super");
            admin.setLastName("Admin");
            admin.setDateOfBirth(LocalDate.of(1990, 1, 1));
            userDb.put(adminId, admin);
        }

        User student1 = new User(UUID.randomUUID().toString(), "student1@uni.edu", passwordService.hash("pass123"), "STUDENT");
        student1.setFirstName("Alice");
        student1.setLastName("Tan");
        student1.setDateOfBirth(LocalDate.of(2002, 5, 10));
        userDb.put(student1.getId(), student1);

        User student2 = new User(UUID.randomUUID().toString(), "student2@uni.edu", passwordService.hash("pass123"), "STUDENT");
        student2.setFirstName("Bob");
        student2.setLastName("Lee");
        student2.setDateOfBirth(LocalDate.of(2001, 8, 20));
        userDb.put(student2.getId(), student2);        
    }

    public Optional<User> findByEmail(String email) {
        return userDb.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public User save(User user) {
        userDb.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userDb.get(id));
    }

    public List<User> findAll() {
        return new ArrayList<>(userDb.values());
    }

}