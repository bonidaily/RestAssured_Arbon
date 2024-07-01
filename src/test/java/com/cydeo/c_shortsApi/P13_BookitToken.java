package com.cydeo.c_shortsApi;

import com.cydeo.utilities.BookitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P13_BookitToken extends BookitTestBase {
      static String accessToken;

    @Test
    void getToken(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email","lfinnisz@yolasite.com")
                .queryParam("password","lissiefinnis")
                .and()
                .get("/sign")
                .then()
                .statusCode(200).extract().jsonPath();

        accessToken = jsonPath.getString("accessToken");
        System.out.println(accessToken);

        Response response = given()
                .accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .and()
                .get("/api/users/me");

        response.prettyPrint();

    }


}
