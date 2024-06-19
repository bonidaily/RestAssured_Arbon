package com.cydeo.homeworkApi.homework03;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Task02 extends ZippopotamTestBase {
    /*
    Given Accept application/json
    And path zipcode is 50000
    When I send a GET request to /us endpoint
    Then status code must be 404
    And content type must be application/json
     */

    @Test
    public void test1(){
        given().pathParam("zipcode", "50000")
                .get("/us/{zipcode}")
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON);

    }
}
