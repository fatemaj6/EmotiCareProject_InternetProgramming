package com.example.springwebapp.chatbot.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springwebapp.chatbot.model.ChatMessage;
import com.example.springwebapp.chatbot.service.ChatbotService;

@Controller
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;
    private final List<ChatMessage> conversation = new ArrayList<>();

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @GetMapping
    public String chatPage(Model model) {
        model.addAttribute("conversation", conversation);
        return "chatbot/chat"; // JSP view
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message, Model model) {
        // Add user message
        ChatMessage userMsg = new ChatMessage("USER", message);
        conversation.add(userMsg);

        // Get bot response
        ChatMessage botMsg = chatbotService.getResponse(message);
        conversation.add(botMsg);

        model.addAttribute("conversation", conversation);
        return "chatbot/chat";
    }
}

