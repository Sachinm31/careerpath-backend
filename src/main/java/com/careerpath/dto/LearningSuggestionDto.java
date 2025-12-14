package com.careerpath.dto;

public class LearningSuggestionDto {
    private String skillName;
    private String priority; // HIGH, MEDIUM, LOW
    private String reason;
    private String learningResourceUrl; // optional

    // getters/setters
    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getLearningResourceUrl() { return learningResourceUrl; }
    public void setLearningResourceUrl(String learningResourceUrl) { this.learningResourceUrl = learningResourceUrl; }
}

