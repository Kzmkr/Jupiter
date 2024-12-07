package org.example.jupiter.api.repository;

import org.example.jupiter.api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
   Teacher findByEmail(String email);
}



