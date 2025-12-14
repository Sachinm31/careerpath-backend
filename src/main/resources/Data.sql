-- USERS (DO NOT INSERT ID)
INSERT INTO users (name, email, password, college, degree, about)
VALUES
    ('Rahul', 'rahul@test.com', '123456', 'VTU', 'Information Science', 'Backend Developer'),
    ('Sachin', 'sachin@test.com', '123456', 'CMR University', 'CSE', 'Full Stack Developer');

-- JOB ROLES
INSERT INTO job_roles (title, description, required_skills)
VALUES
    ('Backend Developer', 'Spring Boot backend role', 'Java,Spring Boot,MySQL'),
    ('Frontend Developer', 'React frontend role', 'React,JavaScript,HTML,CSS');

-- SKILLS (user_id assumes Rahul=1, Sachin=2 on fresh DB)
INSERT INTO skills (name, description, level, category, user_id)
VALUES
    ('Java', 'Core Java', 'Intermediate', 'Programming', 1),
    ('Spring Boot', 'Backend framework', 'Beginner', 'Framework', 1),
    ('React', 'Frontend library', 'Intermediate', 'Frontend', 2);

-- PROJECTS
INSERT INTO projects (title, description, link, user_id)
VALUES
    ('CareerPath+', 'Job recommendation system', 'https://github.com/demo', 1);
