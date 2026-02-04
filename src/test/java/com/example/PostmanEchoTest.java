package com.example;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTest {

    @Test
    void testPostRequestWithSimpleData() {
        given()
                .baseUri("https://postman-echo.com")
                .body("some data")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("some data");
    }

    @Test
    void testPostRequestWithRussianText() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("Привет, мир!")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("Привет, мир!"));
    }

    @Test
    void testPostRequestWithJsonData() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("{\"test\": \"value\"}")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.test", equalTo("value"));
    }

    // Тест, который должен упасть - демонстрация fail в CI
    @Test
    void testFailingPostRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .body("correct data")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("wrong data")); // Намеренно неверное утверждение
    }
}