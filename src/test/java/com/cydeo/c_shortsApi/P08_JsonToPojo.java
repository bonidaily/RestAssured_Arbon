package com.cydeo.c_shortsApi;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P08_JsonToPojo extends SpartanTestBase {
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
                .contentType(ContentType.JSON).extract().jsonPath();

        Spartan spartan = jsonPath.getObject("", Spartan.class);

        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getPhone() = " + spartan.getPhone());
    }


}
