package org.example.jupiter.apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.jupiter.api.model.Student;
import org.example.jupiter.api.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        studentRepository.deleteAll();
        studentRepository.save(new Student("John", "Doe", "john.doe@example.com", "password", "STUDENT"));
    }

    @Test
    public void testAddStudent() {
        given()
                .auth().basic("admin", "admin")
                .param("firstName", "Jane")
                .param("lastName", "Doe")
                .param("email", "jane.doe@example.com")
                .param("semester", "1")
                .param("password", "password")
                .when()
                .post("/addStudent")
                .then()
                .statusCode(HttpStatus.OK.value());

        Student student = studentRepository.findByEmail("jane.doe@example.com");
        assert student != null;
        assert student.getFirstName().equals("Jane");
        assert student.getLastName().equals("Doe");
        assert student.getEmail().equals("jane.doe@example.com");
    }

    @Test
    public void testGetStudent() {
        Student student = studentRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", student.getId())
                .when()
                .get("/student")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"))
                .body("email", equalTo("john.doe@example.com"));
    }



    @Test
    public void testUpdateStudent() {
        Student student = studentRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", student.getId())
                .param("firstName", "Johnny")
                .when()
                .post("/updateStudent")
                .then()
                .statusCode(HttpStatus.OK.value());

        student = studentRepository.findById(student.getId()).orElse(null);
        assert student != null;
        assert student.getFirstName().equals("Johnny");
    }

    @Test
    public void testDeleteStudent() {
        Student student = studentRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", student.getId())
                .when()
                .delete("/student")
                .then()
                .statusCode(HttpStatus.OK.value());

        student = studentRepository.findById(student.getId()).orElse(null);
        assert student == null;
    }
}