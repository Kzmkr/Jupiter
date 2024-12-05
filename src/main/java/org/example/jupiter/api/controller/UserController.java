package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.User;
import org.example.jupiter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
    List<User> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
}

    @GetMapping("/user")
    public User getUser(@RequestParam String id){


        return userService.getUserById(id);

    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email){
        userService.addUser(new User(firstName, lastName, email));
       return ResponseEntity.ok("");

    }

    @DeleteMapping("/user")
    public void delUser(@RequestParam String id){
        userService.deleteUser(id);
    }

}