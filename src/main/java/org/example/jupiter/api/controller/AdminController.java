package org.example.jupiter.api.controller;


import org.example.jupiter.api.model.Class;
import org.example.jupiter.api.model.User;
import org.example.jupiter.service.ClassService;
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
    private final ClassService classService;

    @Autowired
    public AdminController(UserService userService, ClassService classService){
        this.userService = userService;
        this.classService = classService;

    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<User> users = userService.getAllUsers();
        List<Class> classes = classService.getAll();

        model.addAttribute("users", users);
        model.addAttribute("classes", classes);
        return "admin";
    }
}
