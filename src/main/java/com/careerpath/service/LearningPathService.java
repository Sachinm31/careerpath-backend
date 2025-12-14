package com.careerpath.service;

import com.careerpath.dto.LearningSuggestionDto;

import java.util.List;

public interface LearningPathService {

    List<LearningSuggestionDto> suggestNextSkills(Long userId);

    List<LearningSuggestionDto> suggestNextForJobRole(Long jobRoleId);
}
