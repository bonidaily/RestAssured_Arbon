package com.cydeo.c_shortsApi;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P15_JsonSchemaValidation extends SpartanTestBase {
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .pathParam("id",5)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/SingleSpartanSchema.json"));
    }
}
