package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.Student;
import org.example.jupiter.api.model.Teacher;
import org.example.jupiter.api.model.User;
import org.example.jupiter.service.StudentService;
import org.example.jupiter.service.TeacherService;
import org.example.jupiter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,StudentService studentService,TeacherService teacherService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.passwordEncoder = passwordEncoder;
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

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password, @RequestParam String role) {
        if ("STUDENT".equalsIgnoreCase(role)) {
            studentService.addStudent(new Student(firstName, lastName, email, passwordEncoder.encode(password), role));
        } else if ("USER".equalsIgnoreCase(role)) {
            User user = new User( firstName, lastName, email, passwordEncoder.encode(password), role);
            userService.addUser(user);
        }
        else if ("TEACHER".equalsIgnoreCase(role)) {
            teacherService.addTeacher(new Teacher(firstName, lastName, email, passwordEncoder.encode(password), role));
        }
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam String id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String password) {
        userService.updateUser(id, firstName, lastName, email, password);
        return ResponseEntity.ok("User updated successfully");
    }


    @DeleteMapping("/user")
    public void delUser(@RequestParam String id){
        userService.deleteUser(id);
    }

}