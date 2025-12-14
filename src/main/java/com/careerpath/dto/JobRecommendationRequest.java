package com.careerpath.dto;

import java.util.List;

/**
 * Optional advanced request to run recommendation over an arbitrary payload.
 */
public class JobRecommendationRequest {
    private List<String> skills;
    private List<String> interests;
    private List<String> projectNames;

    // getters/setters
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
    public List<String> getProjectNames() { return projectNames; }
    public void setProjectNames(List<String> projectNames) { this.projectNames = projectNames; }
}
