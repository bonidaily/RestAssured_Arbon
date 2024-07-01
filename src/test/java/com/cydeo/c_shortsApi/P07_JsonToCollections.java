package com.cydeo.c_shortsApi;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P07_JsonToCollections extends SpartanTestBase {
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
    void JsonToMap(){
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id", "3")
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().jsonPath();

        Map<String, Object> map = jsonPath.getMap("");
        System.out.println("map = " + map);

        //    id is 3
        assertEquals(3,map.get("id"));

        //         name is "Fidole"
        assertEquals("Fidole",map.get("name"));

        //         gender is "Male"
        assertEquals("Male",map.get("gender"));

        //         phone is 6105035231
        assertEquals(6105035231l,map.get("phone"));
    }

    @Test
    void jsonToList() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().jsonPath();

        List<Map<String ,Object>> listOfMaps = jsonPath.getList("");

        for (Map<String, Object> listOfMap : listOfMaps) {
            System.out.println(listOfMap);
        }

    }

    @Test
    void partiallyMap() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().jsonPath();

        Map<String, Object> map = jsonPath.getMap("[1]");

        System.out.println(map);


    }
}
