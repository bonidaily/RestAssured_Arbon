package com.cydeo.c_shortsApi;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P06_HamCrestMatchers extends SpartanTestBase {
    /*
        /*
    Given accept type is JSON
    And path param id is 3
    When user sends GET request /api/spartans/{id}
    Then response status code should be 200
    And response content type is application/json
    And response payload/body values are
         id is 3
         name is "Fidole"
         gender is "Male"
         phone is 6105035231
     */

    @Test
    public void test1(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id", "3")
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(3))
                .body("name", is("Fidole"))
                .body("gender", is("Male"))
                .body("phone", is(6105035231l)).extract().jsonPath();

        //get me id information
        int anInt = jsonPath.getInt("id");
        System.out.println("anInt = " + anInt);


    }
}
