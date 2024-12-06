package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.Student;
import org.example.jupiter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentPageController {

    private final StudentService studentService;

    @Autowired
    public StudentPageController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public String getStudentProfile(@PathVariable String id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "student";
    }
}