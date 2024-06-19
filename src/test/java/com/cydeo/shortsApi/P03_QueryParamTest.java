package com.cydeo.shortsApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P03_QueryParamTest {
    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://3.208.19.164:8000";
    }
    /*
    Given accept type is json
    and query parameters values are
    gender | female
    nameContains | J
    When user sends get request /api/spartans/search
    Then response status code should be 200
    and response content type is application/json
    and "female" should be in response
    and "Janette" should be in response
     */

    @Test
    void queryParamTest() {

        Response response = RestAssured.given().accept(ContentType.JSON).and().queryParam("nameContains", "J").queryParam("gender", "Female").when().get("/api/spartans/search");

        // Then response status code should be 200
        Assertions.assertEquals(200,response.getStatusCode());

        //    and response content type is application/json
        Assertions.assertEquals(ContentType.JSON.toString(),response.getContentType());

        //    and "female" should be in response
        Assertions.assertTrue(response.asString().contains("Female"));

        //    and "Janette" should be in response
        Assertions.assertTrue(response.asString().contains("Janette"));

        response.prettyPrint();




    }
}
