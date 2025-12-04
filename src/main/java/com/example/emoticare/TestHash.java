package com.example.emoticare;
import com.example.emoticare.auth.service.PasswordService;

public class TestHash {
    public static void main(String[] args) {
        String passwordToHash = "demo";
        String hashedPassword = PasswordService.hashPassword(passwordToHash);
        System.out.println("Hash for 'demo' is: " + hashedPassword);
    }
}