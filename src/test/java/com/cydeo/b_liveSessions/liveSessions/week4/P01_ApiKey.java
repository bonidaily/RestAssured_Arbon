package com.cydeo.b_liveSessions.liveSessions.week4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class P01_ApiKey {
    String token = "WxcuvdoEIJsig89jxqTlGs6WzZIe_n-6aQXHnzpVrcA6t12Q";

    @Test
    public void test1(){

       String apiKey=  System.getenv("APIKEY");

        System.out.println("apiKey = " + apiKey);

        RestAssured.given().accept(ContentType.JSON)
                .baseUri("https://api.currentsapi.services/v1")
                .queryParam("apiKey",apiKey)
                .when()
                .get("/latest-news").prettyPeek()
                .then()
                .statusCode(200);

    }
}
