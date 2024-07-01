package com.cydeo.c_shortsApi;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P11_DeleteSpartan extends SpartanTestBase {

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .pathParam("id",175)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void test2(){
        given()
                .pathParam("id",175)
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(404);
    }
}
