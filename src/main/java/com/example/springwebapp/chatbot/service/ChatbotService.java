package com.example.springwebapp.chatbot.service;

import org.springframework.stereotype.Service;

import com.example.springwebapp.chatbot.model.ChatMessage;

@Service
public class ChatbotService {

    public ChatMessage getResponse(String userInput) {
        String lower = userInput.toLowerCase();
        String reply;

        if (lower.contains("stress") || lower.contains("exam")) {
            reply = "Exams can be stressful. Try breaking study sessions into smaller chunks and take short breaks.";
        } else if (lower.contains("sleep")) {
            reply = "Good sleep is vital. Aim for 7–8 hours and avoid screens before bedtime.";
        } else if (lower.contains("lonely") || lower.contains("friends")) {
            reply = "Feeling lonely is common at university. Reach out to classmates or join a club to connect.";
        } else if (lower.contains("anxious") || lower.contains("anxiety")) {
            reply = "When anxiety rises, deep breathing or a short walk can help calm your mind.";
        } else {
            reply = "I'm here to support you. Could you tell me more about how you're feeling?";
        }

        return new ChatMessage("BOT", reply);
    }
}
