package com.example.emoticare.admin.service;
import com.example.emoticare.auth.service.AuthService;
import com.example.emoticare.auth.model.User;
import java.util.List;

public class AdminService {
    private AuthService authService;

    public AdminService() { this.authService = new AuthService(); }

    public List<User> getAllUsers() { return authService.getAllUsers(); }

    public int getTotalUsers() { return getAllUsers().size(); }

    public int getActiveUsers() {
        return (int) getAllUsers().stream().filter(u -> !u.isAdmin()).count();
    }

    public int getAdminUsers() {
        return (int) getAllUsers().stream().filter(User::isAdmin).count();
    }
}