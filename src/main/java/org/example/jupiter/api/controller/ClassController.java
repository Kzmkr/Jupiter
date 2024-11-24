package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/class")
    public Class getClass(@RequestParam Integer id) {

        return classService.getById(id);
    }

    @GetMapping("/classes")
    public ResponseEntity<List<Class>> getClasses() {
        List<Class> class_ = classService.getAll();
        return new ResponseEntity<>(class_, HttpStatus.OK);
    }


    @PostMapping("/class")
    public void addClass(@RequestParam String name, @RequestParam String time, @RequestParam String day){ {
        classService.addstudent(new Class(name,time,day));
    }
}}
