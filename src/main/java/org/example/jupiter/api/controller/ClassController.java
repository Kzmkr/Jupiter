package org.example.jupiter.api.controller;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/addClass")
    public void addClass(@RequestParam String name, @RequestParam String time, @RequestParam String day) {
        {
            classService.addclass(new Class(name, time, day));
        }

    }
    @PutMapping("/updateClass")
    public ResponseEntity<Class> updateClass(@RequestParam Integer id, @RequestParam String name) {
        Class class_ = classService.getById(id);
        if (class_ == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        class_.setName(name);
        classService.addclass(class_);
        return new ResponseEntity<>(class_, HttpStatus.OK);
    }

    @DeleteMapping("/class")
    public ResponseEntity<Void> deleteClass(@RequestParam Integer id) {
        classService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
