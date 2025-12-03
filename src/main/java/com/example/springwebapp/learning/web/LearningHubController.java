package com.example.springwebapp.learning.web;

import com.example.springwebapp.learning.model.Resource;
import com.example.springwebapp.learning.repo.ResourceRepository;
import com.example.springwebapp.learning.service.LearningHubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/learning")
public class LearningHubController {

    private final LearningHubService service;

    public LearningHubController() {
        this.service = new LearningHubService(new ResourceRepository());
    }

    // UC005: View Awareness Resources
    @GetMapping("/resources")
    public String viewResources(Model model) {
        List<Resource> resources = service.getAllResources();
        model.addAttribute("resources", resources);
        return "learning/resources"; // JSP: /WEB-INF/views/learning/resources.jsp
    }

    // UC006: Take Quiz
    @GetMapping("/quiz/{id}")
    public String takeQuiz(@PathVariable("id") String id, Model model) {
        service.getQuizById(id).ifPresent(r -> model.addAttribute("quiz", r));
        return "learning/quiz"; // JSP: /WEB-INF/views/learning/quiz.jsp
    }

    // UC007: Track Progress
    @GetMapping("/progress")
    public String viewProgress(HttpServletRequest request, Model model) {
        String userId = (String) request.getSession().getAttribute("AUTH_USER_ID");
        String summary = service.getProgressSummary(userId);
        model.addAttribute("progress", summary);
        return "learning/progress"; // JSP: /WEB-INF/views/learning/progress.jsp
    }

    // UC008: Manage Resources (Admin only)
    @PostMapping("/add")
    public String addResource(@RequestParam("title") String title,
                              @RequestParam("type") String type,
                              @RequestParam("content") String content,
                              @RequestParam("duration") int duration,
                              Model model) {
        service.addResource(title, type, content, duration);
        model.addAttribute("msg", "Resource added successfully");
        return "redirect:/learning/resources";
    }

    @PostMapping("/delete/{id}")
    public String deleteResource(@PathVariable("id") String id) {
        service.deleteResource(id);
        return "redirect:/learning/resources";
    }

    // Extra: Dashboard view (styled like your mockups)
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {
        List<Resource> resources = service.getAllResources();
        String userId = (String) request.getSession().getAttribute("AUTH_USER_ID");
        String progress = service.getProgressSummary(userId);

        model.addAttribute("resources", resources);
        model.addAttribute("progress", progress);
        return "learning/dashboard"; // JSP: /WEB-INF/views/learning/dashboard.jsp
    }
}
