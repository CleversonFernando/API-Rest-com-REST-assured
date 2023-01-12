package com.cleversonfernando.rest;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class FileTest {

    @Test
    public void deveObrigarEnvioArquivo() {

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
    public void deveFazerUploadDoArquivo() {

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

    @Test
    public void naodeveFazerUploadDeArquivoGrande() {

        given()
                .log().all()
                .multiPart("arquivo", new File("src/main/resources/Arquivo1MB+.pdf"))
                .when()
                .post("https://restapi.wcaquino.me/upload")
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(413)

        ;
    }
    @Test
    public void deveBaixarArquivo() throws IOException {

        byte[] image = given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/download")
                .then()
                .statusCode(200)
                .extract().asByteArray();

        File imagem = new File("src/main/resources/file.jpg");
        OutputStream out = new FileOutputStream(imagem);
        out.write(image);
        out.close();

  //    System.out.println(imagem.length());
        assertThat(imagem.length(), lessThan(100000L));

    }
}
