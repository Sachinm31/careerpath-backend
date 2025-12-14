package com.careerpath.repository;

import com.careerpath.model.Project;
import com.careerpath.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // find projects by the owning user's id
    List<Project> findByUserId(Long userId);

    // alternative: find by the User entity
    List<Project> findByUser(User user);
}
