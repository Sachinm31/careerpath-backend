package com.careerpath.controller;

import com.careerpath.payload.ApiResponse;
import com.careerpath.payload.LoginRequest;
import com.careerpath.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var user = userService.authenticate(req.getEmail(), req.getPassword());
        if (user == null) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid email or password"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Login successful", user));
    }
}
