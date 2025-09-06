package com.example.employeemanager.service;

import com.example.employeemanager.model.Role;
import com.example.employeemanager.model.User;
import com.example.employeemanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User registerNewUser(String username, String rawPassword) {
        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(rawPassword);  // ❌ Không mã hoá password
        u.setRole(Role.EMPLOYEE);
        return userRepository.save(u);
    }

    public void changeRole(Long userId, Role role) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        u.setRole(role);
        userRepository.save(u);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
