package com.careerpath.service.impl;

import com.careerpath.dto.SkillDto;
import com.careerpath.exception.ResourceNotFoundException;
import com.careerpath.model.Skill;
import com.careerpath.model.User;
import com.careerpath.repository.SkillRepository;
import com.careerpath.repository.UserRepository;
import com.careerpath.service.SkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillServiceImpl(SkillRepository skillRepository,
                            UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    // -------------------------------
    // Add skills to a user
    // -------------------------------
    @Override
    public void addSkillsForUser(Long userId, List<SkillDto> skills) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + userId)
                );

        for (SkillDto dto : skills) {
            Skill skill = new Skill();
            skill.setName(dto.getName().trim());
            skill.setDescription(dto.getDescription());
            skill.setLevel(dto.getLevel());
            skill.setCategory(dto.getCategory());

            // owning side
            skill.setUser(user);

            skillRepository.save(skill);
        }
    }

    // -------------------------------
    // Get skills for a user
    // -------------------------------
    @Override
    public List<Skill> getSkillsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + userId)
                );
        return user.getSkills();
    }

    // -------------------------------
    // Remove skill from user
    // -------------------------------
    @Override
    public void removeSkillFromUser(Long userId, Long skillId) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found: " + skillId)
                );

        // optional safety check
        if (!skill.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Skill does not belong to this user");
        }

        skillRepository.delete(skill);
    }

    // -------------------------------
    // Catalog APIs
    // -------------------------------
    @Override
    public Skill createSkill(SkillDto dto) {
        Skill skill = new Skill();
        skill.setName(dto.getName().trim());
        skill.setDescription(dto.getDescription());
        skill.setLevel(dto.getLevel());
        skill.setCategory(dto.getCategory());
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllCatalogSkills() {
        return skillRepository.findAll();
    }
}
