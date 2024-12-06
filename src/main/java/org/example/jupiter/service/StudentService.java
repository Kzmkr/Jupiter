package org.example.jupiter.service;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.api.model.Student;
import org.example.jupiter.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


import org.example.jupiter.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class StudentService extends UserService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        super(userRepository);
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student getById(String id) {
        return studentRepository.findById(id).orElse(null);
    }


    public void addClassToStudent(String studentId, Class classToAdd) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.getClasses().add(classToAdd);
            studentRepository.save(student);
        }
    }

}


