package com.example.emoticare.auth.web;

import com.example.emoticare.auth.model.User;
import com.example.emoticare.auth.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth/*")
public class AuthServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private AuthService authService;
    
    @Override
    public void init() throws ServletException {
        this.authService = new AuthService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if ("/login".equals(pathInfo)) {
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } else if ("/register".equals(pathInfo)) {
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        } else if ("/profile".equals(pathInfo)) {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
            request.getRequestDispatcher("/WEB-INF/views/auth/profile.jsp").forward(request, response);
        } else if ("/logout".equals(pathInfo)) {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if ("/login".equals(pathInfo)) {
            handleLogin(request, response);
        } else if ("/register".equals(pathInfo)) {
            handleRegister(request, response);
        }
    }
    
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("📝 AuthServlet.handleLogin()");
        System.out.println("   Email from form: " + email);
        System.out.println("   Password from form: " + (password != null ? "[hidden]" : "null"));
        
        User user = authService.loginUser(email, password);
        
        if (user != null) {
            System.out.println("✅ LOGIN SUCCESSFUL - Setting session");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("isAdmin", user.isAdmin());
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } else {
            System.out.println("❌ LOGIN FAILED - Redirecting to login page");
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }
    
    private void handleRegister(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (authService.registerUser(fullName, email, password, confirmPassword)) {
            request.setAttribute("success", "Registration successful! Please login.");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Email may already exist.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        }
    }
    
}
