package com.example.emoticare.chatbot.service;

public class ChatbotService {

    public String generateResponse(String userMessage) {
        String message = userMessage.toLowerCase();

        if (message.contains("hello") || message.contains("hi")) {
            return "Hello! I'm your AI companion. How can I help you today?";
        }
        if (message.contains("anxiety")) {
            return "Anxiety is a common experience. Would you like to learn breathing techniques or coping strategies?";
        }
        if (message.contains("stress")) {
            return "Stress management is important. Try deep breathing, meditation, or physical exercise.";
        }
        if (message.contains("sad") || message.contains("depression")) {
            return "I'm sorry you're feeling down. Would you like to try a mood-lifting activity or speak with a counselor?";
        }
        if (message.contains("sleep")) {
            return "Good sleep is crucial. Try maintaining a consistent schedule and avoiding screens before bed.";
        }

        return "Thank you for sharing. Remember, you're not alone. Would you like to explore our learning resources or speak with someone?";
    }
}