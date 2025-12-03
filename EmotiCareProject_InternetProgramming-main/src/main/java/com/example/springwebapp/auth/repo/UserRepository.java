package com.example.springwebapp.auth.repo;

import com.example.springwebapp.auth.model.User;
import com.example.springwebapp.auth.service.PasswordService;
import org.springframework.stereotype.Repository; // WAJIB

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository // ANOTASI INI WAJIB
public class UserRepository {

    // Simulasi database di memori
    private final ConcurrentHashMap<String, User> userDb = new ConcurrentHashMap<>();
    private final PasswordService passwordService;

    // Gunakan Constructor Injection untuk PasswordService
    public UserRepository(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    // Hanya untuk testing/simulasi
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
}