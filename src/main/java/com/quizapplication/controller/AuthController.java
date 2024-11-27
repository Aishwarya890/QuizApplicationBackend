package com.quizapplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapplication.DTO.LoginDTO;
import com.quizapplication.DTO.LoginResponse;
import com.quizapplication.DTO.RegisterDTO;
import com.quizapplication.entity.AdminRequest;
import com.quizapplication.entity.User;
import com.quizapplication.service.AdminRequestService;
import com.quizapplication.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:3000","https://quizapplicationbackend-production.up.railway.app"})
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        try {
            userService.registerUser(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getPassword(),
                    registerDTO.getConfirmPassword(), registerDTO.isAdmin());
            return ResponseEntity.ok("Registration successful. Please check your email.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Autowired
    private AdminRequestService adminRequestService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
        
        if (user != null) {
            Long userId = user.getId(); // Get the user ID
            boolean isAdmin = user.isAdmin(); // Check if the user is an admin
            
            List<AdminRequest> pendingRequests = adminRequestService.getPendingRequestsForUser(user); // Fetch pending requests
            System.out.println("Is Admin: " + isAdmin + " " + "Id" + userId);
            
            // Correctly pass parameters to the LoginResponse constructor
            return ResponseEntity.ok(new LoginResponse(pendingRequests.isEmpty(), userId, isAdmin, "Login successful!"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed!");
        }
    }


}
