package com.example.emoticare.auth.service;

import com.example.emoticare.auth.model.User;
import com.example.emoticare.auth.repo.UserRepository;
import java.util.List;

public class AuthService {
    
    private UserRepository userRepository;
    
    public AuthService() {
        this.userRepository = new UserRepository();
    }
    
    public boolean registerUser(String fullName, String email, String password, String confirmPassword) {
        
        // Validation
        if (fullName == null || fullName.trim().isEmpty() || 
            email == null || !email.contains("@") || 
            password == null || password.length() < 8 || 
            !password.equals(confirmPassword)) {
            return false;
        }
        
        // Check if email already exists
        if (userRepository.getUserByEmail(email) != null) {
            return false;
        }
        
        // Hash password and create user
        String hashedPassword = PasswordService.hashPassword(password);
        User user = new User(fullName, email, hashedPassword);
        
        return userRepository.registerUser(user);
    }
    
    public User loginUser(String email, String password) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔍 LOGIN ATTEMPT");
        System.out.println("=".repeat(60));
        System.out.println("Email: " + email);
        System.out.println("Password length: " + (password != null ? password.length() : "null"));
        
        User user = userRepository.getUserByEmail(email);
        
        if (user == null) {
            System.out.println("❌ USER NOT FOUND in database");
            System.out.println("=".repeat(60) + "\n");
            return null;
        }
        
        System.out.println("✅ USER FOUND: " + user.getFullName());
        System.out.println("DB Email: " + user.getEmail());
        
        boolean pwdMatch = PasswordService.verifyPassword(password, user.getPassword());
        System.out.println("Password Match: " + pwdMatch);
        
        if (pwdMatch) {
            System.out.println("✅ LOGIN SUCCESS!");
            System.out.println("=".repeat(60) + "\n");
            return user;
        } else {
            System.out.println("❌ PASSWORD MISMATCH");
            System.out.println("=".repeat(60) + "\n");
            return null;
        }
    }
    
    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }
    
    public boolean updateUserProfile(User user) {
        return userRepository.updateUser(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    
}
