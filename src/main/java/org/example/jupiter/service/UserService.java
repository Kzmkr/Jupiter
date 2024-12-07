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

    public void updateUser(String id, String firstName, String lastName, String email, String password) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (firstName != null && ! firstName.isEmpty()) {
                user.setFirstName(firstName);
            }
            if (lastName != null && ! lastName.isEmpty()) {
                user.setLastName(lastName);
            }
            if (email != null && ! email.isEmpty()) {
                user.setEmail(email);
            }
            if (password != null && ! password.isEmpty()) {
                user.setPassword(password);
            }
            userRepository.save(user);
        }
    }


    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}


