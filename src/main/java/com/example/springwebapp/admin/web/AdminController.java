package com.example.springwebapp.admin.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springwebapp.admin.service.AdminService;
import com.example.springwebapp.auth.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // UC014: Admin Report
    @GetMapping("/report")
    public String report(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("AUTH_USER_ROLE"))) {
            return "redirect:/auth/login";
        }
        model.addAttribute("pageTitle", "Admin Report");
        model.addAttribute("report", adminService.generateReport());
        return "admin/report";
    }

    // UC015: Manage Users
    @GetMapping("/users")
    public String users(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("AUTH_USER_ROLE"))) {
            return "redirect:/auth/login";
        }
        List<User> users = adminService.listUsers();
        model.addAttribute("pageTitle", "Manage Users");
        model.addAttribute("users", users);
        return "admin/users";
    }

    @PostMapping("/users/{id}/suspend")
    public String suspend(@PathVariable String id) {
        adminService.suspendUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable String id) {
        adminService.deleteUser(id);
        return "redirect:/admin/users";
    }
}

