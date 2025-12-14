package com.careerpath.service.impl;

import com.careerpath.dto.UserDto;
import com.careerpath.model.User;
import com.careerpath.repository.UserRepository;
import com.careerpath.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserDto dto) {

        // check duplicate email
        userRepository.findByEmailIgnoreCase(dto.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCollege(dto.getCollege());
        user.setDegree(dto.getDegree());
        user.setAbout(dto.getAbout());

        User saved = userRepository.save(user);
        saved.setPassword(null); // hide password
        return saved;
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setPassword(null);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    @Override
    public User authenticate(String email, String rawPassword) {
        return userRepository.findByEmailIgnoreCase(email)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .map(u -> {
                    u.setPassword(null);
                    return u;
                })
                .orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        user.setName(dto.getName());
        user.setCollege(dto.getCollege());
        user.setDegree(dto.getDegree());
        user.setAbout(dto.getAbout());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        User updated = userRepository.save(user);
        updated.setPassword(null);
        return updated;
    }
}
