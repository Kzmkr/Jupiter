package org.example.jupiter.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private String time;

    @Column(name = "day_of_week")
    private String dayOfWeek;


    @ManyToMany(mappedBy = "classes")
    @JsonIgnore
    private Set<Student> students;

    public Class() {
    }

    public Class(String name, String time, String dayOfWeek) {
        this.name = name;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
    }
    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    @JsonProperty("studentIds")
    public Set<String> getStudentIds() {
        return students.stream().map(Student::getId).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Class [id=" + id + ", name=" + name + "]";
    }
}