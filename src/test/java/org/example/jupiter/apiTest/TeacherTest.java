package org.example.jupiter.apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.jupiter.api.model.Teacher;
import org.example.jupiter.api.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeacherTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        teacherRepository.deleteAll();
        teacherRepository.save(new Teacher("Alice", "Smith", "alice.smith@example.com", "password", "TEACHER"));
    }

    @Test
    public void testAddTeacher() {
        given()
                .auth().basic("admin", "admin")
                .param("firstName", "Bob")
                .param("lastName", "Johnson")
                .param("email", "bob.johnson@example.com")
                .param("password", "password")
                .when()
                .post("/addTeacher")
                .then()
                .statusCode(HttpStatus.OK.value());

        Teacher teacher = teacherRepository.findByEmail("bob.johnson@example.com");
        assert teacher != null;
        assert teacher.getFirstName().equals("Bob");
        assert teacher.getLastName().equals("Johnson");
        assert teacher.getEmail().equals("bob.johnson@example.com");
    }

    @Test
    public void testGetTeacher() {
        Teacher teacher = teacherRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", teacher.getId())
                .when()
                .get("/teacher")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("firstName", equalTo("Alice"))
                .body("lastName", equalTo("Smith"))
                .body("email", equalTo("alice.smith@example.com"));
    }

    @Test
    public void testUpdateTeacher() {
        Teacher teacher = teacherRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", teacher.getId())
                .param("firstName", "Alicia")
                .when()
                .post("/updateTeacher")
                .then()
                .statusCode(HttpStatus.OK.value());

        teacher = teacherRepository.findById(teacher.getId()).orElse(null);
        assert teacher != null;
        assert teacher.getFirstName().equals("Alicia");
    }

    @Test
    public void testDeleteTeacher() {
        Teacher teacher = teacherRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", teacher.getId())
                .when()
                .delete("/teacher")
                .then()
                .statusCode(HttpStatus.OK.value());

        teacher = teacherRepository.findById(teacher.getId()).orElse(null);
        assert teacher == null;
    }
}