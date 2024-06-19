package com.cydeo.liveRecordings.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("Get /api/spartans as guest user -> except 401")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .get("/api/spartans")
                .then()
                .statusCode(401);
    }
}
