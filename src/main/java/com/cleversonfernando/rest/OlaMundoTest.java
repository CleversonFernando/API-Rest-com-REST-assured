package com.cleversonfernando.rest;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class OlaMundoTest {

    @Test
    public void testOlaMundo(){
        Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue(response.statusCode() == 200);
        Assert.assertTrue("O status code deveria ser 200", response.statusCode() == 200);
        Assert.assertEquals(200, response.statusCode());


        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);
    }

    @Test

    public void devoConhecerOutrasFormasRestAssured(){
        Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");

        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);

        get("http://restapi.wcaquino.me/ola").then().statusCode(200);

        given()
                .when()
                    .get("http://restapi.wcaquino.me/ola")
                .then()
                    .statusCode(200);
    }

    @Test
    public void devoConhecerOsMetodosComHamcrest(){
        assertThat("Maria", Matchers.is("Maria"));
        assertThat(128, is(128));
        assertThat(128, isA(Integer.class));
        assertThat(128d, isA(Double.class));
        assertThat(128d, greaterThan(120d));
        assertThat(128d, lessThan(130d));


        List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1,3,5,7,9));
        assertThat(impares, containsInAnyOrder(1,3,5,9,7));
        assertThat(impares, hasItems(1, 5));

        assertThat("Maria", is(not("Joao")));
        assertThat("Maria", not("Joao"));
        assertThat("Maria", anyOf(is("Maria"), is("joana")));
        assertThat("joana", allOf(startsWith("joa"), endsWith("ana"), containsString("oana")));
    }
    @Test
    public void devoValidarBody(){

        given()
                .when()
                .get("http://restapi.wcaquino.me/ola")
                .then()
                .statusCode(200)
                .body(is("Ola Mundo!"))
                .body(containsString("Mundo"))
                .body(is(not(nullValue())));

    }
}
