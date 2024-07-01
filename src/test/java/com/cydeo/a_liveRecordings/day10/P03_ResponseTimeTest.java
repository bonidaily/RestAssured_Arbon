package com.cydeo.a_liveRecordings.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P03_ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(both(greaterThan(500L)).and(lessThan(1500L))).extract().response();

        long time = response.time();

        System.out.println("time = " + time);
    }
}
