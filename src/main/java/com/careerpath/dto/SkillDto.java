package com.careerpath.dto;

import jakarta.validation.constraints.NotBlank;

public class SkillDto {

    @NotBlank
    private String name;

    private String description;

    // Beginner / Intermediate / Advanced
    private String level;

    private String category;

    // getters / setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
