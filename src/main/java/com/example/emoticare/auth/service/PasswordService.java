package com.example.emoticare.auth.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordService {
    
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean verifyPassword(String password, String hash) {
        String hashOfInput = hashPassword(password);
        return hashOfInput != null && hashOfInput.equals(hash);
    }
    
}
