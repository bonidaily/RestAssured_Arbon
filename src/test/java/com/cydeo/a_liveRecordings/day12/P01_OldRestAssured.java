package com.cydeo.a_liveRecordings.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P01_OldRestAssured extends SpartanNewTestBase {

    @Test
    public void getAllSpartans() {
        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .body("id[1]", is(2)).log().body();
    }

    @Test
    public void getAllSpartansOldWay() {
        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .body("id[1]", is(2))
                .when()
                .get("/spartans");

    }
}
