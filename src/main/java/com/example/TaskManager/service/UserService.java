package com.example.TaskManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TaskManager.Model.User;
import com.example.TaskManager.dto.AuthResponse;
import com.example.TaskManager.dto.LoginRequest;
import com.example.TaskManager.dto.RegisterRequest;
import com.example.TaskManager.repository.UserRepository;
import com.example.TaskManager.security.JwtService;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponse register(RegisterRequest request) {
        // 1. Check if user already exists
        if (userRepository.findByEmail(request.email).isPresent()) {
            System.out.println("this is err                  +++++++++++++");
            throw new RuntimeException("User already exists with this email");
        }

        // 2. Create new user
        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password)); // Encrypt password

        // 3. Save to database
        userRepository.save(user);

        // 4. Generate JWT token
        String token = jwtService.generateToken(user);
        System.out.println("Registered User: " + user.getEmail());
        System.out.println("Generated Token: " + token);

        // 5. Return token in response
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
