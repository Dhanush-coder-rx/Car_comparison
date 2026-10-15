package com.example.api.Service;

import com.example.api.Entity.User;
import com.example.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean checkUserExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User registerUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setUsertype("User"); // Set UserType to "User"
        return userRepository.save(user);
    }
}