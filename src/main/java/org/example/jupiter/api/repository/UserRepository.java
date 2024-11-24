package org.example.jupiter.api.repository;


import org.example.jupiter.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface UserRepository extends JpaRepository<User, Integer> {
    }

