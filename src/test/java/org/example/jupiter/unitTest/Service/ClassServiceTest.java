package org.example.jupiter.unitTest.Service;

import org.example.jupiter.api.model.Class;
import org.example.jupiter.api.repository.ClassRepository;
import org.example.jupiter.service.ClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClassServiceTest {

    @Mock
    private ClassRepository classRepository;

    @InjectMocks
    private ClassService classService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddClass() {
        Class class_ = new Class();
        when(classRepository.save(class_)).thenReturn(class_);

        Class result = classService.addclass(class_);
        assertNotNull(result);
        verify(classRepository, times(1)).save(class_);
    }

    @Test
    void testGetById() {
        Class class_ = new Class();
        when(classRepository.findById(1)).thenReturn(Optional.of(class_));

        Class result = classService.getById(1);
        assertNotNull(result);
        verify(classRepository, times(1)).findById(1);
    }

    @Test
    void testGetAll() {
        Class class1 = new Class();
        Class class2 = new Class();
        List<Class> classList = Arrays.asList(class1, class2);
        when(classRepository.findAll()).thenReturn(classList);

        List<Class> result = classService.getAll();
        assertEquals(2, result.size());
        verify(classRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        doNothing().when(classRepository).deleteById(1);

        classService.deleteById(1);
        verify(classRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteAll() {
        doNothing().when(classRepository).deleteAll();

        classService.deleteAll();
        verify(classRepository, times(1)).deleteAll();
    }
}