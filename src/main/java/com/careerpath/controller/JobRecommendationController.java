package com.careerpath.controller;

import com.careerpath.dto.JobRecommendationDto;
import com.careerpath.dto.JobRecommendationRequest;
import com.careerpath.model.JobRole;
import com.careerpath.service.JobRecommendationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Recommendation endpoints.
 */
@RestController
@RequestMapping("/api/recommend")
public class JobRecommendationController {

    private final JobRecommendationService jobRecommendationService;

    public JobRecommendationController(JobRecommendationService jobRecommendationService) {
        this.jobRecommendationService = jobRecommendationService;
    }

    // Get job role suggestions for a user
    @GetMapping("/jobs/user/{userId}")
    public ResponseEntity<List<JobRecommendationDto>> recommendJobsForUser(
            @PathVariable("userId") Long userId) {

        return ResponseEntity.ok(
                jobRecommendationService.recommendJobsForUser(userId)
        );
    }

    // POST request with custom payload
    @PostMapping("/jobs")
    public ResponseEntity<List<JobRecommendationDto>> recommendJobs(
            @Valid @RequestBody JobRecommendationRequest request) {

        return ResponseEntity.ok(
                jobRecommendationService.recommendJobs(request)
        );
    }

    // Get openings for a job role
    @GetMapping("/openings/{jobRoleId}")
    public ResponseEntity<List<JobRole>> getOpenings(
            @PathVariable("jobRoleId") Long jobRoleId) {

        return ResponseEntity.ok(
                jobRecommendationService.getOpeningsForRole(jobRoleId)
        );
    }
}
