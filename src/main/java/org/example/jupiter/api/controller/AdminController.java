package org.example.jupiter.api.controller;


import org.example.jupiter.api.model.User;
import org.example.jupiter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class AdminController {


    private final UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;

    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }
}
