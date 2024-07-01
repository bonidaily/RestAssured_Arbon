package com.cydeo.c_shortsApi;

import com.cydeo.utilities.NewsApiTestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P14_NewsApi extends NewsApiTestBase {
    
    @Test
    public void getNews(){
        given().log().uri()
                .queryParam("q","bitcoin")
                .header("x-api-key","653fa8c0fc50420ca139da8535fd4d2d")
                .when()
                .get("/everything")
                .then()
                .statusCode(200);
    }
}
