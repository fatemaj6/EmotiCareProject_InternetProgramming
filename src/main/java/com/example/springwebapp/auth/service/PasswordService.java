package com.example.springwebapp.auth.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public String hash(String rawPassword) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(rawPassword, salt);
    }

    public boolean matches(String rawPassword, String hash) {
        if (hash == null) return false;
        return BCrypt.checkpw(rawPassword, hash);
    }
}

