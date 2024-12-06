package org.example.jupiter.api.model;

import jakarta.persistence.*;
import org.example.jupiter.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;


    public User() {

    }

    public User(String firstName, String lastName, String email, String password, String role) {
        this.id = generateRandomId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }




    private String generateRandomId() {

        int length = 5;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                id.append(characters.charAt(random.nextInt(characters.length())));
            }


        return id.toString();
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }
}
