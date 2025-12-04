package com.example.emoticare.learning.web;
import com.example.emoticare.learning.model.Resource;
import com.example.emoticare.learning.service.LearningHubService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/learning/*")
public class LearningHubServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LearningHubService learningHubService;

    @Override
    public void init() throws ServletException { this.learningHubService = new LearningHubService(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String pathInfo = request.getPathInfo();
        if ("/hub".equals(pathInfo)) {
            List<Resource> resources = learningHubService.getAllResources();
            request.setAttribute("resources", resources);
            request.getRequestDispatcher("/WEB-INF/views/learning/dashboard.jsp").forward(request, response);
        }
    }
}