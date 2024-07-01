package com.cydeo.c_shortsApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P02_PathParamTest {

     /*
    Given content type is application/json
    And id parameter value is 3
    When user Sends get requests /api/spartans/{id}
    Then user should be able to see status code is 200
    And content type is application/json
     */



     @BeforeAll
     public static void init(){

         RestAssured.baseURI="http://3.208.19.164:8000";

     }
    @Test
    void pathParamTest() {

        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParam("id",3).when().get("/api/spartans/{id}");

        Assertions.assertEquals(200,response.getStatusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        Assertions.assertEquals("application/json",response.getContentType());
        System.out.println("response.getContentType() = " + response.getContentType());

        response.prettyPrint();
    }
}
