package org.example.jupiter.unitTest.Service;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.api.model.Student;
import org.example.jupiter.api.repository.StudentRepository;
import org.example.jupiter.api.repository.UserRepository;
import org.example.jupiter.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudent() {
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.addStudent(student);
        assertNotNull(result);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetById() {
        Student student = new Student();
        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        Student result = studentService.getById("1");
        assertNotNull(result);
        verify(studentRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        studentService.updateStudent("1", "John", "Doe", "john.doe@example.com", "password", "semester");
        verify(studentRepository, times(1)).save(student);
    }

}