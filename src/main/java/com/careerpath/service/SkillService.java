package com.careerpath.service;

import com.careerpath.dto.SkillDto;
import com.careerpath.model.Skill;

import java.util.List;

public interface SkillService {

    Skill createSkill(SkillDto dto);

    void addSkillsForUser(Long userId, List<SkillDto> skills);

    List<Skill> getSkillsForUser(Long userId);

    void removeSkillFromUser(Long userId, Long skillId);

    List<Skill> getAllCatalogSkills();
}
