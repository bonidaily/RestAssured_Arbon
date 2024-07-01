package com.cydeo.b_liveSessions.liveSessions.week4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P04_JsonSchemaValidation extends SpartanTestBase {

    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/AllSpartansSchema.json"));
    }
}
