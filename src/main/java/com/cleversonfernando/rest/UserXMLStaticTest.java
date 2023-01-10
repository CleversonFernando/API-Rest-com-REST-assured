package com.cleversonfernando.rest;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserXMLStaticTest {

    @BeforeClass
    public static void setup() {
        baseURI = "http://restapi.wcaquino.me/";
//      port = 80;
//      basePath = "";
    }
    @Test
    public void devoTrabalharComXML() {


        given()
                .log().all()
                .when()
                .get("/usersXML/3")
                .then()
                .statusCode(200)
                .body("user.name", is("Ana Julia"))
                .body("user.@id", is("3"))
                .body("user.filhos.name.size()", is(2))
                .body("user.filhos.name[0]", is("Zezinho"))
                .body("user.filhos.name[1]", is("Luizinho"))
                .body("user.filhos.name", hasItem("Zezinho"))
                .body("user.filhos.name", hasItems("Zezinho", "Luizinho"))

        ;
    }
}
