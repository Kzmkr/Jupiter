package org.example.jupiter.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity

@Inheritance(strategy = InheritanceType.JOINED)
public class Student extends User {

    @Column(name = "semester")
    private String studentsemester;


    @ManyToMany
    @JoinTable(
            name = "student_class",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @JsonIgnore
    private Set<Class> classes;


    public Student() {
    }

    public Student(String firstName, String lastName, String email, String semester) {
        super(firstName, lastName, email);
        this.studentsemester = semester;
    }

    public String getStudentsemester() {
        return studentsemester;
    }

    public void setStudentsemester(String studentsemester) {
        this.studentsemester = studentsemester;
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
        return "Student [studentId=" + studentsemester + ", " + super.toString() + "]";
    }
}