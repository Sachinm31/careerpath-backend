
# CareerPath+ Backend 🚀  
Skill & Career Recommendation System (Java | Spring Boot)

## Project Overview

CareerPath+ is a backend-focused **Spring Boot application** that helps users understand which job roles best match their current skills and what they should learn next to grow their career.

The system analyzes user skills, compares them with industry job requirements, calculates match percentages, highlights skill gaps, and suggests prioritized learning paths.

This project is built specifically as a **resume-ready Java backend project**, demonstrating real-world backend development practices such as clean architecture, REST APIs, database design, and business logic implementation.

---

## Tech Stack

- **Java 21**
- **Spring Boot 4**
- **Spring Data JPA (Hibernate)**
- **Spring Security (Basic Authentication)**
- **MySQL**
- **Maven**
- **Docker (planned)**

---

## Key Features

### User Management & Authentication
- User registration and login APIs
- Password encryption using **BCrypt**
- Secure handling of sensitive fields (password hidden from responses)

### Skill Management
- Add, view, and remove skills for a user
- Proper **JPA relationships** (One User → Many Skills)
- Clean ownership mapping with foreign keys

### Job Recommendation Engine
- Matches user skills with job role requirements
- Calculates **match percentage** for each job role
- Clearly separates:
  - Matched skills
  - Missing skills
- Job roles are sorted by best match first

### Learning Path Suggestions
- Identifies most common missing skills across job roles
- Assigns priority levels:
  - **HIGH**
  - **MEDIUM**
  - **LOW**
- Helps users focus on high-impact skills first

### Persistent Storage
- Uses **MySQL** for permanent data storage
- Auto-increment numeric IDs
- Proper foreign key constraints for data integrity

---

## How the Recommendation Logic Works

1. Fetch the user’s existing skills
2. Fetch all available job roles
3. For each job role:
   - Compare required skills with user skills
   - Identify matched and missing skills
   - Calculate match percentage
4. Sort job roles by match percentage (highest first)
5. Aggregate missing skills to generate learning suggestions

---

## Project Structure

```

src/main/java/com/careerpath
│
├── controller       // REST API endpoints
├── service          // Business logic interfaces
├── service/impl     // Core business logic
├── repository       // Database access (JPA)
├── model            // Entity classes
├── dto              // Data Transfer Objects
├── payload          // Request/response payloads
├── exception        // Global & custom exceptions
└── config           // Security and app configuration

````

---

## API Endpoints

### Authentication
- `POST /api/auth/login`  
  Login user

### User
- `POST /api/users/register`  
  Register new user

### Skills
- `POST /api/skills/user/{userId}`  
  Add skills for user
- `GET /api/skills/user/{userId}`  
  Get user skills

### Job Recommendations
- `GET /api/recommend/jobs/user/{userId}`  
  Get job recommendations with match percentage

### Learning Path
- `GET /api/learning/next/user/{userId}`  
  Get prioritized learning suggestions

---

## Sample API Response

### Job Recommendation

```json
[
  {
    "title": "Backend Developer",
    "matchPercentage": 66,
    "matchedSkills": ["java", "spring boot"],
    "missingSkills": ["mysql"]
  }
]
````

### Learning Suggestions

```json
[
  {
    "skillName": "Docker",
    "priority": "HIGH",
    "reason": "Popular missing skill across multiple job roles"
  }
]
```

---

## Database Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/upvisor
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

---

## How to Run the Project

1. Clone the repository

   ```bash
   git clone https://github.com/your-username/careerpath-backend.git
   ```
2. Create a MySQL database named `upvisor`
3. Update database credentials in `application.properties`
4. Run the application

   ```bash
   mvn clean spring-boot:run
   ```
5. Server runs at:

   ```
   http://localhost:8080
   ```

---

## Why This Project Is Resume-Worthy

* Strong **Java + Spring Boot backend focus**
* Real-world **business logic**, not just CRUD
* Clean architecture and proper layering
* Demonstrates database relationships and constraints
* Shows understanding of authentication and security basics
* Perfect for **Java Backend Developer / Fresher roles**

---

## Future Enhancements

* JWT-based authentication
* Dockerized deployment
* Admin APIs for job role management
* Integration with external learning platforms

---

## Author

**Sachin**
Aspiring Java Backend Developer

---

⭐ If you like this project, feel free to star the repository!

```
