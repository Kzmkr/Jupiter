package org.example.jupiter.apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.jupiter.api.model.Class;
import org.example.jupiter.api.repository.ClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private ClassRepository classRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        classRepository.deleteAll();
        classRepository.save(new Class("Math", "10:00 AM", "Monday"));
    }

    @Test
    public void testAddClass() {
        given()
                .auth().basic("admin", "admin")
                .param("name", "Science")
                .param("time", "11:00 AM")
                .param("day", "Tuesday")
                .when()
                .post("/addClass")
                .then()
                .statusCode(HttpStatus.OK.value());

        Class class_ = classRepository.findByName("Science");
        assert class_ != null;
        assert class_.getName().equals("Science");
        assert class_.getTime().equals("11:00 AM");
        assert class_.getDayOfWeek().equals("Tuesday");
    }

    @Test
    public void testGetClass() {
        Class class_ = classRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", class_.getId())
                .when()
                .get("/class")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("name", equalTo("Math"))
                .body("time", equalTo("10:00 AM"))
                .body("dayOfWeek", equalTo("Monday"));
    }
    @Test
    public void testUpdateClass() {
        Class class_ = classRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", class_.getId())
                .param("name", "Physics")
                .when()
                .put("/updateClass")
                .then()
                .statusCode(HttpStatus.OK.value());

        class_ = classRepository.findById(class_.getId()).orElse(null);
        assert class_ != null;
        assert class_.getName().equals("Physics");
    }

    @Test
    public void testDeleteClass() {
        Class class_ = classRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", class_.getId())
                .when()
                .delete("/class")
                .then()
                .statusCode(HttpStatus.OK.value());

        class_ = classRepository.findById(class_.getId()).orElse(null);
        assert class_ == null;
    }
}