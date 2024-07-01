package com.cydeo.c_shortsApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanTest {
    String url = "http://3.208.19.164:8000";

    @Test
    void getAllSpartans() {


        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans");



        //verify status code
        Assertions.assertEquals(200,response.getStatusCode());

        //verify content-type
        Assertions.assertEquals("application/json",response.getContentType());

        System.out.println("-----------------------Response AsString------------------------------");
        System.out.println("response.asString() = " + response.asString());

        System.out.println("-----------------------Response prettyPrint------------------------------");
        response.prettyPrint();

    }
}
