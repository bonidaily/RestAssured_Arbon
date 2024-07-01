package com.cydeo.a_liveRecordings.day10;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P04_JsonSchemaValidation extends SpartanTestBase {

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .pathParam("id",1)
                .when()
                .get("api/spartans/{id}")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/SingleSpartanSchema.json"));
    }

    @DisplayName("GET /api/spartans/search to validate with JsonSchemaValidator")
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .queryParam("nameContains","J")
                .queryParam("gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/SearchSpartanSchema.json"));

    }

    @DisplayName("Get all spartans and validate JsonSchema")
    @Test
    public void test3(){

        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/AllSpartansSchema.json"));

    }

    @DisplayName("Post a Spartan and validate JsonSchema")
    @Test
    public void test4(){
        Spartan spartan = new Spartan();
        spartan.setName("Boni");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("api/spartans")
                .then()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/SpartanPostSchema.json"));
    }
}
