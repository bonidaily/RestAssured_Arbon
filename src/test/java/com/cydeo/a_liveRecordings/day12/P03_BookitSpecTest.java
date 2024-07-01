package com.cydeo.a_liveRecordings.day12;

import com.cydeo.utilities.BookitTestBase;
import com.cydeo.utilities.BookitUtils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class P03_BookitSpecTest extends BookitTestBase {

    /*
    send a request /api/users/me endpoint as teacher
    verify status code 200
    content type will be contentype.json
     */

    @Test
    public void test1(){
       given().spec(BookitUtils.getReqSpec("teacher"))
               .when().get("/api/users/me")
               .then()
               .spec(BookitUtils.getRespSpec(200));





    }
}
