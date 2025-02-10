package com.dockerSample.dockerSample.contollers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dockerSample.dockerSample.entity.User;

import jakarta.annotation.PostConstruct;

@RestController
public class AuthController {

    private final Map<String, String> users = new ConcurrentHashMap<>(); // Replace with database

    @PostConstruct
    public void init() {
        users.put("testuser", "86546f8d-3391-4290-aebd-7d153471bdec"); // Example with bcrypt
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String storedPassword = users.get(user.getUsername());

        if (storedPassword != null && storedPassword.equals("86546f8d-3391-4290-aebd-7d153471bdec")) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }
}