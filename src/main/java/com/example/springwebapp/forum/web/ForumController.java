package com.example.springwebapp.forum.web;

import com.example.springwebapp.forum.model.ForumPost;
import com.example.springwebapp.forum.service.ForumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {
    private final ForumService service;

    public ForumController(ForumService service) {
        this.service = service;
    }

    @GetMapping
    public String listPosts(@RequestParam(value = "category", required = false) String category,
                            Model model) {
        List<ForumPost> posts;
        if (category != null && !category.isEmpty()) {
            posts = service.getPostsByCategory(category);
        } else {
            posts = service.getAllPosts();
        }
        model.addAttribute("posts", posts);
        model.addAttribute("selectedCategory", category);
        return "forum/list";
    }

    @GetMapping("/new")
    public String newPostForm(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("AUTH_USER_ID");
        if (userId == null) {
            return "redirect:/auth/login";
        }
        return "forum/new";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("category") String category,
                             HttpServletRequest request,
                             Model model) {
        String userId = (String) request.getSession().getAttribute("AUTH_USER_ID");
        String userEmail = (String) request.getSession().getAttribute("AUTH_USER_EMAIL");

        if (userId == null) {
            return "redirect:/auth/login";
        }
        ForumPost post = service.createPost(userId, userEmail, title, content, category);
        return "redirect:/forum/post/" + post.getId();
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable("id") String id, Model model) {
        service.getPostById(id).ifPresent(post -> model.addAttribute("post", post));
        return "forum/view";
    }

    @PostMapping("/post/{id}/comment")
    public String addComment(@PathVariable("id") String postId,
                             @RequestParam("content") String content,
                             HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("AUTH_USER_ID");
        String userEmail = (String) request.getSession().getAttribute("AUTH_USER_EMAIL");

        if (userId == null) {
            return "redirect:/auth/login";
        }
        service.addComment(postId, userId, userEmail, content);
        return "redirect:/forum/post/" + postId;
    }

    @PostMapping("/post/{id}/delete")
    public String deletePost(@PathVariable("id") String id, HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("AUTH_USER_ROLE");
        if ("ADMIN".equals(role)) {
            service.deletePost(id);
        }
        return "redirect:/forum";
    }

    @PostMapping("/post/{id}/pin")
    public String togglePin(@PathVariable("id") String id, HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("AUTH_USER_ROLE");
        if ("ADMIN".equals(role)) {
            service.togglePinPost(id);
        }
        return "redirect:/forum/post/" + id;
    }
}
