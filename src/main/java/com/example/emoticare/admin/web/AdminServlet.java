package com.example.emoticare.admin.web;
import com.example.emoticare.admin.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService;

    @Override
    public void init() throws ServletException { this.adminService = new AdminService(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null || !((Boolean) session.getAttribute("isAdmin"))) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String pathInfo = request.getPathInfo();
        if ("/dashboard".equals(pathInfo)) {
            request.setAttribute("totalUsers", adminService.getTotalUsers());
            request.setAttribute("activeUsers", adminService.getActiveUsers());
            request.setAttribute("adminUsers", adminService.getAdminUsers());
            request.setAttribute("users", adminService.getAllUsers());
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
        }
    }
}