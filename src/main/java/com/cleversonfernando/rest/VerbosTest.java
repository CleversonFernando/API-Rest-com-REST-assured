package com.cleversonfernando.rest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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

    @Test
    public void naoDeveSalvarUsuarioSemNome(){

        given()
                .when()
                .log().all()
                .contentType("application/json")
                .body("{\"age\": 50}")
                .post("Http://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(400)
                .body("id", is(nullValue()))
                .body("error", is("Name é um atributo obrigatório"))

        ;
    }
}
