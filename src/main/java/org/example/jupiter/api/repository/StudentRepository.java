package org.example.jupiter.api.repository;

import org.example.jupiter.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> { }



