package com.example.emoticare.screening.web;
import com.example.emoticare.screening.service.ScreeningService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/screening/*")
public class ScreeningServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ScreeningService screeningService;

    @Override
    public void init() throws ServletException { this.screeningService = new ScreeningService(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/screening/assessment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String type = request.getParameter("type");
        int score = Integer.parseInt(request.getParameter("score"));
        String responses = request.getParameter("responses");

        if (screeningService.submitAssessment(userId, type, score, responses)) {
            request.setAttribute("success", "Assessment submitted successfully!");
        }
        request.getRequestDispatcher("/WEB-INF/views/screening/result.jsp").forward(request, response);
    }
}