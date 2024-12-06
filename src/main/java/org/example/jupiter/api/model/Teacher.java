package org.example.jupiter.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity

@Inheritance(strategy = InheritanceType.JOINED)
public class Teacher extends User {


    @ManyToMany
    @JoinTable(
            name = "teacher_class",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @JsonIgnore
    private Set<Class> classes;


    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email, String password,String role) {
        super(firstName, lastName, email,password,role);
    }

    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }
    @JsonProperty("classIds")
    public Set<Integer> getClassIds() {
        return classes.stream().map(Class::getId).collect(Collectors.toSet());
    }



    @Override
    public String toString() {
        return "Teacher [teacherId="  + ", " + super.toString() + "]";
    }
}