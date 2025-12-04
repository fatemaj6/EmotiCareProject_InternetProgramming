package com.example.emoticare.forum.web;
import com.example.emoticare.forum.service.ForumService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/forum/*")
public class ForumServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ForumService forumService;

    @Override
    public void init() throws ServletException { this.forumService = new ForumService(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String pathInfo = request.getPathInfo();
        if ("/list".equals(pathInfo)) {
            request.setAttribute("posts", forumService.getAllPosts());
            request.getRequestDispatcher("/WEB-INF/views/forum/list.jsp").forward(request, response);
        } else if ("/create".equals(pathInfo)) {
            request.getRequestDispatcher("/WEB-INF/views/forum/create.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");

        if (forumService.createPost(userId, title, content, category)) {
            response.sendRedirect(request.getContextPath() + "/forum/list");
        }
    }
}