package com.careerpath.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple root endpoint to show the service is up.
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok(
                java.util.Map.of(
                        "app", "CareerPath+ Backend",
                        "status", "running",
                        "h2_console", "/h2-console",
                        "api_root", "/api"
                )
        );
    }
}
