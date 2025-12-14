package com.careerpath.service.impl;

import com.careerpath.dto.JobRecommendationDto;
import com.careerpath.dto.JobRecommendationRequest;
import com.careerpath.model.JobRole;
import com.careerpath.model.Skill;
import com.careerpath.model.User;
import com.careerpath.repository.JobRoleRepository;
import com.careerpath.repository.UserRepository;
import com.careerpath.service.JobRecommendationService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobRecommendationServiceImpl implements JobRecommendationService {

    private final JobRoleRepository jobRoleRepository;
    private final UserRepository userRepository;

    public JobRecommendationServiceImpl(JobRoleRepository jobRoleRepository,
                                        UserRepository userRepository) {
        this.jobRoleRepository = jobRoleRepository;
        this.userRepository = userRepository;
    }

    // ============================
    // RECOMMEND JOBS FOR USER
    // ============================
    @Override
    public List<JobRecommendationDto> recommendJobsForUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + userId)
                );

        // User skills → lowercase set
        Set<String> userSkills = user.getSkills().stream()
                .map(Skill::getName)
                .filter(Objects::nonNull)
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        List<JobRole> jobRoles = jobRoleRepository.findAll();
        List<JobRecommendationDto> recommendations = new ArrayList<>();

        for (JobRole role : jobRoles) {

            // Required skills from job role
            List<String> requiredSkills = parseSkills(role.getRequiredSkills());

            List<String> normalizedRequired = requiredSkills.stream()
                    .map(String::toLowerCase)
                    .toList();

            // Matched skills
            List<String> matchedSkills = normalizedRequired.stream()
                    .filter(userSkills::contains)
                    .toList();

            // Missing skills
            List<String> missingSkills = normalizedRequired.stream()
                    .filter(skill -> !userSkills.contains(skill))
                    .toList();

            // Match percentage
            int matchPercentage = 0;
            if (!normalizedRequired.isEmpty()) {
                matchPercentage =
                        (int) ((matchedSkills.size() * 100.0) / normalizedRequired.size());
            }

            JobRecommendationDto dto = new JobRecommendationDto();
            dto.setJobRoleId(role.getId()); // Long → Long ✔
            dto.setTitle(role.getTitle());
            dto.setDescription(role.getDescription());
            dto.setMatchPercentage(matchPercentage);
            dto.setRequiredSkills(requiredSkills);
            dto.setMatchedSkills(matchedSkills);
            dto.setMissingSkills(missingSkills);

            recommendations.add(dto);
        }

        // Sort by best match first
        recommendations.sort(
                Comparator.comparing(JobRecommendationDto::getMatchPercentage).reversed()
        );

        return recommendations;
    }

    // ============================
    // OPTIONAL: POST BASED RECOMMENDATION
    // ============================
    @Override
    public List<JobRecommendationDto> recommendJobs(JobRecommendationRequest request) {
        return Collections.emptyList();
    }

    // ============================
    // GET OPENINGS
    // ============================
    @Override
    public List<JobRole> getOpeningsForRole(Long jobRoleId) {

        return jobRoleRepository.findById(jobRoleId)
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());
    }

    // ============================
    // HELPER
    // ============================
    private List<String> parseSkills(String skillsCsv) {
        if (skillsCsv == null || skillsCsv.isBlank()) {
            return Collections.emptyList();
        }
        return Arrays.stream(skillsCsv.split("[,;|]"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
