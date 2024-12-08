package org.example.jupiter.unitTest.Service;

import org.example.jupiter.api.model.Student;
import org.example.jupiter.api.model.Teacher;
import org.example.jupiter.api.repository.TeacherRepository;
import org.example.jupiter.api.repository.UserRepository;
import org.example.jupiter.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTeacher() {
        Teacher teacher = new Teacher("John", "Doe", "john.doe@example.com", "password", "TEACHER");
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        Teacher savedTeacher = teacherService.addTeacher(teacher);

        assertNotNull(savedTeacher);
        assertEquals("John", savedTeacher.getFirstName());
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    public void testGetById() {
        Teacher teacher = new Teacher("John", "Doe", "john.doe@example.com", "password", "TEACHER");
        when(teacherRepository.findById("1")).thenReturn(Optional.of(teacher));

        Teacher foundTeacher = teacherService.getById("1");

        assertNotNull(foundTeacher);
        assertEquals("John", foundTeacher.getFirstName());
        verify(teacherRepository, times(1)).findById("1");
    }


    @Test
    void testUpdateStudent() {
        Teacher teacher= new Teacher();
        when(teacherRepository.findById("1")).thenReturn(Optional.of(teacher));

        teacherService.updateTeacher("1", "John", "Doe", "john.doe@example.com", "password");
        verify(teacherRepository, times(1)).save(teacher);
    }


}