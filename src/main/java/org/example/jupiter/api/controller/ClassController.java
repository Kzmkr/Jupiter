package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClassController {

    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/class/{id}")
    public String getClassProfile(@PathVariable Integer id, Model model) {
        Class class_ = classService.getById(id);
        model.addAttribute("class", class_);
        return "class";
    }
}