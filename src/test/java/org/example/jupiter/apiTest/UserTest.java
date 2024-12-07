package org.example.jupiter.apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.jupiter.api.model.User;
import org.example.jupiter.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        userRepository.deleteAll();
        userRepository.save(new User("John", "Doe", "john.doe@example.com", "password", "USER"));
    }

    @Test
    public void testAddUser() {
        given()
                .auth().basic("admin", "admin")
                .param("firstName", "Jane")
                .param("lastName", "Doe")
                .param("email", "jane.doe@example.com")
                .param("password", "password")
                .param("role", "USER")
                .when()
                .post("/addUser")
                .then()
                .statusCode(HttpStatus.OK.value());

        User user = userRepository.findByEmail("jane.doe@example.com");
        assert user != null;
        assert user.getFirstName().equals("Jane");
        assert user.getLastName().equals("Doe");
        assert user.getEmail().equals("jane.doe@example.com");
    }

    @Test
    public void testGetUser() {
        User user = userRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", user.getId())
                .when()
                .get("/user")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"));

    }

    @Test
    public void testUpdateUser() {
        User user = userRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", user.getId())
                .param("firstName", "Johnny")
                .when()
                .put("/updateUser")
                .then()
                .statusCode(HttpStatus.OK.value());

        user = userRepository.findById(user.getId()).orElse(null);
        assert user != null;
        assert user.getFirstName().equals("Johnny");
    }

    @Test
    public void testDeleteUser() {
        User user = userRepository.findAll().get(0);
        given()
                .auth().basic("admin", "admin")
                .param("id", user.getId())
                .when()
                .delete("/user")
                .then()
                .statusCode(HttpStatus.OK.value());

        user = userRepository.findById(user.getId()).orElse(null);
        assert user == null;
    }
}