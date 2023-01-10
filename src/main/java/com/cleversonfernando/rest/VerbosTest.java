package com.cleversonfernando.rest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class VerbosTest {

    @Test
    public void deveSalvarOUsuario(){

        given()
                .when()
                .log().all()
                .contentType("application/json")
                .body("{\"name\": \"José\", \"age\": 50}")
                .post("Http://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", is(notNullValue()))
                .body("name", is("José"))
                .body("age", is(50))

                ;
    }
}
