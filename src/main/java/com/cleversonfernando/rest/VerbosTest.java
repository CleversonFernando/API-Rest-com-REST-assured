package com.cleversonfernando.rest;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class VerbosTest {

    @Test
    public void deveSalvarOUsuario() {

        given()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
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
    public void naoDeveSalvarUsuarioSemNome() {

        given()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"age\": 50}")
                .post("Http://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(400)
                .body("id", is(nullValue()))
                .body("error", is("Name é um atributo obrigatório"))

        ;
    }

    @Test
    public void deveAlterarUsuario() {

        given()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Usuario alterado\", \"age\": 80}")
                .put("Http://restapi.wcaquino.me/users/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is("Usuario alterado"))
                .body("salary", is(1234.5678f))
        ;
    }
    @Test
    public void devoCustomizarURL() {
        given()
                .when()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Usuario alterado\", \"age\": 80}")
                .put("Http://restapi.wcaquino.me/{entidade}/{userId}", "users", "1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is("Usuario alterado"))
                .body("salary", is(1234.5678f))
        ;
    }
    @Test
    public void devoCustomizarURL2() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Usuario alterado\", \"age\": 80}")
                .pathParam("entidade", "users")
                .pathParam("userId", 1)
                .when()
                .put("Http://restapi.wcaquino.me/{entidade}/{userId}")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is("Usuario alterado"))
                .body("salary", is(1234.5678f))
        ;
    }
}