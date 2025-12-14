package com.careerpath.controller;

import com.careerpath.model.JobRole;
import com.careerpath.repository.JobRoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:5173")
public class JobRoleController {

    private final JobRoleRepository repo;

    public JobRoleController(JobRoleRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<JobRole> getAllJobs() {
        return repo.findAll();
    }
}
