package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        Optional<User> user =
                userRepository.findByUsername(loginUser.getUsername());

        if (user.isPresent() &&
            user.get().getPassword().equals(loginUser.getPassword())) {
            return "Login Success";
        }
        return "Invalid Credentials";
    }
}
