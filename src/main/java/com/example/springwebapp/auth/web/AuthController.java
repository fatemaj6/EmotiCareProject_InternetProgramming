package com.example.springwebapp.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springwebapp.auth.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // PENTING: Constructor Injection. Pastikan AuthService di-scan (@Service)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // UC003: Login (page) - Mengembalikan nama view: "auth/login"
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    // UC003: Login (action)
    @PostMapping("/login")
    public String loginAction(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              HttpServletRequest request,
                              Model model) {
        return authService.login(email, password)
                .map(user -> {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("AUTH_USER_ID", user.getId());
                    session.setAttribute("AUTH_USER_EMAIL", user.getEmail());
                    session.setAttribute("AUTH_USER_ROLE", user.getRole());
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/report";
            } 
            else {
                return "redirect:/home";
            }
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Invalid email or password");
                    model.addAttribute("email", email);
                    return "auth/login";
                });
    }

    // UC001: Register (page)
    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }
    
    // ... (Metode registerAction dan lainnya)
    
    // UC004: Logout
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return "redirect:/auth/login";
    }
}