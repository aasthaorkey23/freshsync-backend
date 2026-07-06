package com.Freshsync.freshsync_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Freshsync.freshsync_backend.model.User;
import com.Freshsync.freshsync_backend.repository.UserRepository;

import com.Freshsync.freshsync_backend.security.JwtService;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    public String registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            return "Email already registered";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
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

    public Map<String, String> loginUser(String email, String password) {
        Map<String, String> response = new HashMap<>();

        User user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtService.generateToken(user.getEmail(), user.getId());
            response.put("token", token);
        } else {
            response.put("message", "Invalid email or password");
        }

        return response;
    }
}