package org.example.jupiter.service;

import org.example.jupiter.api.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


import org.example.jupiter.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}


