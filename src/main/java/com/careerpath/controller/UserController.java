package com.careerpath.controller;

import com.careerpath.dto.UserDto;
import com.careerpath.model.User;
import com.careerpath.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            User created = userService.registerUser(userDto);
            return ResponseEntity.status(201).body(created);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of(
                            "success", false,
                            "message", ex.getMessage()
                    ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ DEBUG ENDPOINT
    @GetMapping("/debug/all")
    public ResponseEntity<List<User>> debugAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/info")
    public String info() {
        return "Backend is running!";
    }
}
