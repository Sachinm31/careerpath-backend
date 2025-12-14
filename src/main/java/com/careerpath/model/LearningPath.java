package com.careerpath.model;

import jakarta.persistence.*;

@Entity
@Table(name = "learning_paths")
public class LearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String description;

    // Simple comma-separated skills or JSON
    @Column(length = 2000)
    private String skillSequence;

    public LearningPath() {}

    public LearningPath(Long id, String title, String description, String skillSequence) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.skillSequence = skillSequence;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSkillSequence() { return skillSequence; }
    public void setSkillSequence(String skillSequence) { this.skillSequence = skillSequence; }
}
