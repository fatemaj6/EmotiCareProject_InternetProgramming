// AdminService.java
package com.example.springwebapp.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springwebapp.auth.model.User;
import com.example.springwebapp.auth.repo.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userRepository.seedAdminIfEmpty();
    }

    // UC014: Generate Admin Report
    public List<String> generateReport() {
        List<String> report = new ArrayList<>();
        report.add("Total Users: " + userRepositorySnapshot().size());
        long admins = userRepositorySnapshot().stream().filter(u -> "ADMIN".equals(u.getRole())).count();
        long students = userRepositorySnapshot().stream().filter(u -> "STUDENT".equals(u.getRole())).count();
        report.add("Admins: " + admins);
        report.add("Students: " + students);
        report.add("Dummy Forum Posts: 5"); // seeded dummy data
        return report;
    }

    // UC015: Manage User Accounts
    public List<User> listUsers() {
        return userRepositorySnapshot();
    }

    public void suspendUser(String id) {
        userRepository.findById(id).ifPresent(u -> u.setRole("SUSPENDED"));
    }

    public void deleteUser(String id) {
        userRepository.findById(id).ifPresent(u -> userRepository.save(null));
    }

    private List<User> userRepositorySnapshot() {
        // Add a helper in UserRepository: findAll()
        try {
            return (List<User>) UserRepository.class.getMethod("findAll").invoke(userRepository);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

