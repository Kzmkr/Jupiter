package org.example.jupiter.service;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.api.model.Teacher;
import org.example.jupiter.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


import org.example.jupiter.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class TeacherService extends UserService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository) {
        super(userRepository);
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    public Teacher getById(String id) {
        return teacherRepository.findById(id).orElse(null);
    }


    public void updateTeacher(String id, String firstName, String lastName, String email, String password) {
        super.updateUser(id, firstName, lastName, email, password);
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            teacherRepository.save(teacher);
        }
    }


    public void addClassToTeacher(String teacherId, Class classToAdd) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            teacher.getClasses().add(classToAdd);
            teacherRepository.save(teacher);
        }
    }

}


