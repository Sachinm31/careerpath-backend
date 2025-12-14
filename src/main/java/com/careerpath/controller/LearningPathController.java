package com.careerpath.controller;

import com.careerpath.dto.LearningSuggestionDto;
import com.careerpath.service.LearningPathService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints to suggest next skills/learning path for the user.
 */
@RestController
@RequestMapping("/api/learning")
public class LearningPathController {

    private final LearningPathService learningPathService;

    public LearningPathController(LearningPathService learningPathService) {
        this.learningPathService = learningPathService;
    }

    // Suggest learning items for a user
    @GetMapping("/next/user/{userId}")
    public ResponseEntity<List<LearningSuggestionDto>> suggestNextForUser(
            @PathVariable("userId") Long userId) {

        return ResponseEntity.ok(
                learningPathService.suggestNextSkills(userId)
        );
    }

    // Suggest based on a requested job role
    @GetMapping("/next/job/{jobRoleId}")
    public ResponseEntity<List<LearningSuggestionDto>> suggestNextForJob(
            @PathVariable("jobRoleId") Long jobRoleId) {

        return ResponseEntity.ok(
                learningPathService.suggestNextForJobRole(jobRoleId)
        );
    }
}
