package com.example.emoticare.chatbot.web;
import com.example.emoticare.chatbot.service.ChatbotService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/chatbot/*")
public class ChatbotServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ChatbotService chatbotService;

    @Override
    public void init() throws ServletException { this.chatbotService = new ChatbotService(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/chatbot/chat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        String aiResponse = chatbotService.generateResponse(message);

        response.setContentType("application/json");
        response.getWriter().write("{\"response\": \"" + aiResponse + "\"}");
    }
}