package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.Teacher;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.service.ClassService;
import org.example.jupiter.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;



@RestController
public class TeacherRestController {

    private final TeacherService teacherService;
    private final ClassService classService;

    @Autowired
    public TeacherRestController(TeacherService teacherService, ClassService classService) {
        this.teacherService = teacherService;
        this.classService = classService;
    }

    @GetMapping("/teacher")
    public Teacher getTeacher(@RequestParam String id) {


        return teacherService.getById(id);
    }


    @PostMapping("/addTeacher")
    public ResponseEntity<String> addteacher(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
        teacherService.addTeacher(new Teacher(firstName, lastName, email,password,"TEACHER"));
        return ResponseEntity.ok("");

    }


    @PostMapping("/updateTeacher")
    public ResponseEntity<String> updateTeacher(@RequestParam String id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String password) {
        teacherService.updateTeacher(id, firstName, lastName, email, password);
        return ResponseEntity.ok("Teacher updated successfully");
    }


    @PostMapping("/teacher/addClass")
    public ResponseEntity<String> addClassToStudent(@RequestParam String studentId, @RequestParam Integer classId) {
        Class classToAdd = classService.getById(classId);
        if (classToAdd != null) {
            teacherService.addClassToTeacher(studentId, classToAdd);
            return ResponseEntity.ok("Class added to teacher");
        } else {
            return ResponseEntity.badRequest().body("Class not found");
        }
    }


}
