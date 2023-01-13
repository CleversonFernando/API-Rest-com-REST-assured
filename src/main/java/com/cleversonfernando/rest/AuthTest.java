package com.cleversonfernando.rest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class AuthTest {

    @Test
    public void deveAcessarSWAPI(){
        given()
                .log().all()
                .when()
                .get("https://swapi.dev/api/people/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Luke Skywalker"))
        ;
    }
    // bfc578bd3622f1283af550df97944087
    // https://api.openweathermap.org/data/2.5/weather?q=Fortaleza,BR&appid=bfc578bd3622f1283af550df97944087&units=metric

    @Test
    public void deveObterClima(){

        given()
                .log().all()
                .queryParam("q", "Fortaleza,BR")
                .queryParam("appid", "bfc578bd3622f1283af550df97944087")
                .queryParam("units", "metric")
                .when()
                .get("https://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Fortaleza"))
                .body("coord.lon", is(-38.5247f))
                .body("main.temp", greaterThan(25f))



        ;
    }

}
