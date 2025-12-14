package com.careerpath.service;

import com.careerpath.dto.UserDto;
import com.careerpath.model.User;

import java.util.List;

public interface UserService {

    User registerUser(UserDto dto);

    User getUserById(Long id);

    List<User> getAllUsers();

    User authenticate(String email, String rawPassword);

    void deleteUser(Long id);

    User updateUser(Long id, UserDto dto);
}
