package com.careerpath.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // hide password from JSON output
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private String college;
    private String degree;

    @Column(length = 2000)
    private String about;

    /**
     * One user -> many skills
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Skill> skills = new ArrayList<>();

    public User() {}

    public User(Long id, String name, String email, String password,
                String college, String degree, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.college = college;
        this.degree = degree;
        this.about = about;
    }

    // ---------- Getters / Setters ----------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    // ---------- Skills helpers ----------

    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    public void setSkills(List<Skill> skills) {
        this.skills.clear();
        if (skills != null) {
            skills.forEach(this::addSkill);
        }
    }

    public void addSkill(Skill skill) {
        if (skill == null) return;
        skill.setUser(this);
        this.skills.add(skill);
    }

    public void removeSkill(Skill skill) {
        if (skill == null) return;
        skill.setUser(null);
        this.skills.remove(skill);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", college='" + college + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
