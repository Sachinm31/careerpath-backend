package com.careerpath.service.impl;

import com.careerpath.dto.LearningSuggestionDto;
import com.careerpath.model.JobRole;
import com.careerpath.model.Skill;
import com.careerpath.model.User;
import com.careerpath.repository.JobRoleRepository;
import com.careerpath.repository.UserRepository;
import com.careerpath.service.LearningPathService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation:
 * - Parses JobRole.requiredSkills (CSV/semicolon/pipe) into List<String>
 * - Aggregates most-common missing skills across job roles for a user
 * - For suggestNextForJobRole, returns each required skill as a suggestion
 */
@Service
@Transactional
public class LearningPathServiceImpl implements LearningPathService {

    private final UserRepository userRepository;
    private final JobRoleRepository jobRoleRepository;

    public LearningPathServiceImpl(UserRepository userRepository,
                                   JobRoleRepository jobRoleRepository) {
        this.userRepository = userRepository;
        this.jobRoleRepository = jobRoleRepository;
    }

    @Override
    public List<LearningSuggestionDto> suggestNextSkills(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NoSuchElementException("User not found: " + userId)
                );

        // user's existing skills (normalized)
        Set<String> have = Optional.ofNullable(user.getSkills())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Skill::getName)
                .filter(Objects::nonNull)
                .map(s -> s.trim().toLowerCase())
                .collect(Collectors.toSet());

        // Count how many job roles mention each missing skill
        Map<String, Long> missingCount = new HashMap<>();

        for (JobRole role : jobRoleRepository.findAll()) {
            List<String> reqSkills = parseSkills(role.getRequiredSkills());
            for (String skillName : reqSkills) {
                String normalized = skillName.toLowerCase();
                if (!have.contains(normalized)) {
                    missingCount.put(
                            normalized,
                            missingCount.getOrDefault(normalized, 0L) + 1
                    );
                }
            }
        }

        // Convert map -> sorted list of LearningSuggestionDto
        return missingCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .map(e -> {
                    LearningSuggestionDto dto = new LearningSuggestionDto();
                    dto.setSkillName(e.getKey());
                    dto.setPriority(
                            e.getValue() > 3 ? "HIGH"
                                    : (e.getValue() > 1 ? "MEDIUM" : "LOW")
                    );
                    dto.setReason(
                            "Popular missing skill across roles (" + e.getValue() + " roles)"
                    );
                    dto.setLearningResourceUrl(null);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<LearningSuggestionDto> suggestNextForJobRole(Long jobRoleId) {

        JobRole role = jobRoleRepository.findById(jobRoleId)
                .orElseThrow(() ->
                        new NoSuchElementException("JobRole not found: " + jobRoleId)
                );

        List<String> reqSkills = parseSkills(role.getRequiredSkills());

        return reqSkills.stream()
                .map(skillName -> {
                    LearningSuggestionDto dto = new LearningSuggestionDto();
                    dto.setSkillName(skillName);
                    dto.setPriority("HIGH");
                    dto.setReason("Required by job role: " + role.getTitle());
                    dto.setLearningResourceUrl(null);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // -------------------
    // Helpers
    // -------------------

    private static List<String> parseSkills(String input) {
        if (input == null || input.isBlank()) return Collections.emptyList();

        return Arrays.stream(input.split("[,;|]"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
