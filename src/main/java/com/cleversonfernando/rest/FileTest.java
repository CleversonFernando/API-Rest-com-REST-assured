package com.cleversonfernando.rest;

import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class FileTest {

    @Test
    public void deveObrigarEnvioArquivo(){

        given()
                .log().all()
                .when()
                .post("https://restapi.wcaquino.me/upload")
                .then()
                .log().all()
                .statusCode(404)// Deveria ser 400
                .body("error", is("Arquivo não enviado"))

                ;
    }
    @Test
    public void deveFazerUploadDoArquivo(){

        given()
                .log().all()
                .multiPart("arquivo", new File("src/main/resources/users.pdf"))
                .when()
                .post("https://restapi.wcaquino.me/upload")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("users.pdf"))

        ;
    }
}
