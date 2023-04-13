package ru.autotest.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void getTest() {
        when()
                .get("/store/order/{orderId}", 1)
        .then()
                .assertThat()
                .statusCode(200)
                .and().body("id", equalTo(1));
    }

    @Test
    public void postTest() {
        String body = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\"," +
                "\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).
        with()
                .body(body)
        .when()
                .request(Method.POST, "/pet")
        .then()
                .statusCode(200)
                .and().body("name", equalTo("doggie"));
    }

    @Test
    public void putTest() {
        String body = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\"," +
                "\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).
        with()
                .body(body)
        .when()
                .request(Method.PUT, "/pet")
        .then()
                .statusCode(200)
                .and().body("name", equalTo("doggie"));
    }

    @Test
    public void delTest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .request(Method.DELETE, "/store/order/1")
        .then()
                .statusCode(200);
    }

}
