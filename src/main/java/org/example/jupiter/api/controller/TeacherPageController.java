package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.Teacher;
import org.example.jupiter.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeacherPageController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherPageController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teacher/{id}")
    public String getTeacherProfile(@PathVariable String id, Model model) {
        Teacher teacher = teacherService.getById(id);
        model.addAttribute("teacher", teacher);
        return "teacher";
    }
}