package com.example.CareerBridge.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.CareerBridge.model.JwtResponse;
import com.example.CareerBridge.model.User;
import com.example.CareerBridge.repo.UserRepo;
import com.example.CareerBridge.service.JwtUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        log.info("Signup attempt for username: {}", user.getUsername());

        if (userRepo.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken!");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role if none provided
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_USER");
            user.setRoles(roles);
        }

        userRepo.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public JwtResponse authenticate(@RequestBody User user) {
        log.info("Login attempt for username: {}", user.getUsername());

        User dbUser = userRepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtUtils.generateToken(dbUser.getUsername());

        List<String> rolesList = new ArrayList<>(dbUser.getRoles());

        return new JwtResponse(
                token,
                "Bearer",
                dbUser.getUsername(),
                rolesList
        );
    }
}
