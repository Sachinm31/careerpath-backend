package com.careerpath.service;

import com.careerpath.dto.JobRecommendationDto;
import com.careerpath.dto.JobRecommendationRequest;
import com.careerpath.model.JobRole;

import java.util.List;

public interface JobRecommendationService {

    List<JobRecommendationDto> recommendJobsForUser(Long userId);

    List<JobRecommendationDto> recommendJobs(JobRecommendationRequest request);

    List<JobRole> getOpeningsForRole(Long jobRoleId);
}
