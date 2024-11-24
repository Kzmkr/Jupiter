package org.example.jupiter.service;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.api.repository.ClassRepository;
import org.example.jupiter.api.repository.UserRepository;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ClassService {

    private final ClassRepository classRepository;


    @Autowired
    public ClassService(ClassRepository classRepository, UserRepository userRepository) {

        this.classRepository = classRepository;
    }

    public Class addstudent(Class class_) {
        return classRepository.save(class_);
    }

    public Class getById(Integer id) {
        return classRepository.findById(id).orElse(null);
    }

    public List<Class> getAll() {
        return classRepository.findAll();
    }

    public void deleteById(Integer id) {
        classRepository.deleteById(id);
    }

    public void deleteAll() {
        classRepository.deleteAll();
    }

}