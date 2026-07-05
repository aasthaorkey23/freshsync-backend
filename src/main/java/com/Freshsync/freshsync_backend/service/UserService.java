package com.Freshsync.freshsync_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Freshsync.freshsync_backend.model.User;
import com.Freshsync.freshsync_backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRoleIgnoreCase(role);
    }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            return "Login successful";
        } else {
            return "Invalid email or password";
        }
    }
}