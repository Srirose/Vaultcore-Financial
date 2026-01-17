package com.example.demo.controller;

import com.example.demo.entity.User;

import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import com.example.demo.config.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;



//hello
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository repo, JwtUtil jwt, PasswordEncoder encoder) {
        this.userRepository = repo;
        this.jwtUtil = jwt;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {

        User user = userRepository
                .findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(loginUser.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}
