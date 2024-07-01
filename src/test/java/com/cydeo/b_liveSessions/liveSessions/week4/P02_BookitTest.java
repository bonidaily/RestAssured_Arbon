package com.cydeo.b_liveSessions.liveSessions.week4;

import com.cydeo.utilities.BookitTestBase;
import com.cydeo.utilities.BookitUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P02_BookitTest extends BookitTestBase {
     /*
    credentials:
         "team_leader"
         String email="lfinnisz@yolasite.com";
         String password="lissiefinnis";

     Given I logged bookit api as a "team_leader"
     And Accept header is "application/json"
     When I send GET request to "/api/campuses" endpoint
     Then status code should be 200
     And Response Content type is "application/json"
     And Each "id" field should not be null

     */

    String token = BookitUtils.getToken("lfinnisz@yolasite.com", "lissiefinnis");

    @Test
    public void test1() {

        given().accept(ContentType.JSON)
                .header("Authorization", token)
                .get("/api/campuses")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", everyItem(is(notNullValue())));


    }

    @Test
    void test() {

        RequestSpecification givenPart= RestAssured.given().log().uri();


        //     Given I logged bookit api as a "team_leader"
        givenPart.header("Authorization",token);

        //     And Accept header is "application/json"
        givenPart.accept(ContentType.JSON);


        //     When I send GET request to "/api/campuses" endpoint
        Response response = givenPart.when().get("/api/campuses");
        ValidatableResponse thenPart = response.then();

        //     Then status code should be 200
        thenPart.statusCode(200);

        //     And Response Content type is "application/json"
        thenPart.contentType(ContentType.JSON);

        //     And Each "id" field should not be null
        thenPart.body("id", everyItem(notNullValue()));

        // Maybe I need to retrieve a JSONPATH Object
        JsonPath jp = thenPart.extract().jsonPath();


    }
}
