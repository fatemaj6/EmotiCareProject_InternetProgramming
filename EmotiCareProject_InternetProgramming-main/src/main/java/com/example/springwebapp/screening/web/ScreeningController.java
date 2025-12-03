package com.example.springwebapp.screening.web;

import com.example.springwebapp.screening.model.Screening;
import com.example.springwebapp.screening.service.ScreeningService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/screening")
public class ScreeningController {
    private final ScreeningService service;

    public ScreeningController(ScreeningService service) {
        this.service = service;
    }

    @GetMapping("/start")
    public String startScreening(@RequestParam(value = "type", defaultValue = "DEPRESSION") String type, 
                                  Model model) {
        model.addAttribute("screeningType", type);
        return "screening/start";
    }

    @PostMapping("/submit")
    public String submitScreening(@RequestParam("type") String type,
                                   @RequestParam("score") int score,
                                   HttpServletRequest request,
                                   Model model) {
        String userId = (String) request.getSession().getAttribute("AUTH_USER_ID");
        if (userId == null) {
            return "redirect:/auth/login";
        }
        Screening screening = service.completeScreening(userId, type, score);
        return "redirect:/screening/result/" + screening.getId();
    }

    @GetMapping("/result/{id}")
    public String viewResult(@PathVariable("id") String id, Model model) {
        service.getScreeningById(id).ifPresent(s -> model.addAttribute("screening", s));
        return "screening/result";
    }

    @GetMapping("/history")
    public String viewHistory(HttpServletRequest request, Model model) {
        String userId = (String) request.getSession().getAttribute("AUTH_USER_ID");
        if (userId == null) {
            return "redirect:/auth/login";
        }
        List<Screening> screenings = service.getUserScreenings(userId);
        model.addAttribute("screenings", screenings);
        return "screening/history";
    }
}
