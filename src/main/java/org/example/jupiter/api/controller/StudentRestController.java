package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.Student;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.service.ClassService;
import org.example.jupiter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;



@RestController
public class StudentRestController {

    private final StudentService studentService;
    private final ClassService classService;

    @Autowired
    public StudentRestController(StudentService studentService, ClassService classService) {
        this.studentService = studentService;
        this.classService = classService;
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam String id) {
            return studentService.getById(id);
    }


    @PostMapping("/addStudent")
    public ResponseEntity<String> addstudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String semester, @RequestParam String password) {
        studentService.addStudent(new Student(firstName, lastName, email,password,"STUDENT"));
        return ResponseEntity.ok("");

    }


    @PostMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestParam String id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String password, @RequestParam(required = false) String semester) {
        studentService.updateStudent(id, firstName, lastName, email, password, semester);
        return ResponseEntity.ok("Student updated successfully");
    }


    @PostMapping("/student/addClass")
    public ResponseEntity<String> addClassToStudent(@RequestParam String studentId, @RequestParam Integer classId) {
        Class classToAdd = classService.getById(classId);
        if (classToAdd != null) {
            studentService.addClassToStudent(studentId, classToAdd);
            return ResponseEntity.ok("Class added to student");
        } else {
            return ResponseEntity.badRequest().body("Class not found");
        }
    }

    @DeleteMapping("/student")
    public ResponseEntity<String> deleteStudent(@RequestParam String id) {
        studentService.deleteUser(id);
        return ResponseEntity.ok("Student deleted successfully");
    }


}
