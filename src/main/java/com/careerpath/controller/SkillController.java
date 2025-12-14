package com.careerpath.controller;

import com.careerpath.dto.SkillDto;
import com.careerpath.model.Skill;
import com.careerpath.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints to add/list skills for users and to manage skills catalog.
 */
@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // Save or add a skill to the system (catalog/admin)
    @PostMapping("/catalog")
    public ResponseEntity<Skill> createSkillInCatalog(@Valid @RequestBody SkillDto dto) {
        Skill created = skillService.createSkill(dto);
        return ResponseEntity.ok(created);
    }

    // Add skills for a given user
    @PostMapping("/user/{userId}")
    public ResponseEntity<Void> addSkillsForUser(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody List<SkillDto> skills) {

        skillService.addSkillsForUser(userId, skills);
        return ResponseEntity.ok().build();
    }

    // Get user's skills
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Skill>> getUserSkills(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(skillService.getSkillsForUser(userId));
    }

    // Remove a skill from a user
    @DeleteMapping("/user/{userId}/{skillId}")
    public ResponseEntity<Void> removeSkillFromUser(
            @PathVariable("userId") Long userId,
            @PathVariable("skillId") Long skillId) {

        skillService.removeSkillFromUser(userId, skillId);
        return ResponseEntity.noContent().build();
    }

    // List all catalog skills
    @GetMapping("/catalog")
    public ResponseEntity<List<Skill>> listCatalogSkills() {
        return ResponseEntity.ok(skillService.getAllCatalogSkills());
    }
}
