package com.example.springwebapp.auth.service;

import com.example.springwebapp.auth.model.User;
import com.example.springwebapp.auth.repo.UserRepository;
import org.springframework.stereotype.Service; // WAJIB

import java.util.Optional;
import java.util.UUID;

@Service // ANOTASI INI WAJIB
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    // PENTING: Constructor Injection. Pastikan komponen lain di-scan
    public AuthService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.userRepository.seedAdminIfEmpty(); 
    }

    public User register(String email, String rawPassword, String role) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (rawPassword == null || rawPassword.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        String id = UUID.randomUUID().toString();
        String hash = passwordService.hash(rawPassword);
        User user = new User(id, email, hash, role == null ? "STUDENT" : role);
        return userRepository.save(user);
    }

    public Optional<User> login(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .filter(u -> passwordService.matches(rawPassword, u.getPasswordHash()));
    }
    
    // ... (Metode lainnya)
}