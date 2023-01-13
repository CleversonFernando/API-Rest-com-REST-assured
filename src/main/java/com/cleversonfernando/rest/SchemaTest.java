package com.cleversonfernando.rest;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Test;
import org.xml.sax.SAXException;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

public class SchemaTest {

    @Test
    public void deveValidarSchemaXML() {

        given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/usersXML")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesXsdInClasspath("users.xsd"))

        ;

    }

    @Test(expected = SAXException.class)
    public void naoDeveValidarSchemaXML() {

        given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/invalidUsersXML")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesXsdInClasspath("users.xsd"))

        ;

    }

    @Test
    public void deveValidarSchemaJson() {

        given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"))

        ;
    }
}
