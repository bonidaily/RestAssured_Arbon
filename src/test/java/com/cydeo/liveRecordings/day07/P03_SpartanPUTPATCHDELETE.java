package com.cydeo.liveRecordings.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class P03_SpartanPUTPATCHDELETE extends SpartanTestBase {
    @DisplayName("PUT spartan With Map")
    @Test
    public void test1() {
        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "John Doe PUT");
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("phone", 8877445596l);

        //PUT WILL update existing record so we choose one the existing ID, make sure it exists in your IP ADDRESs
        int id = 137;

        JsonPath jsonPath = given().log().body().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBodyMap)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204).extract().jsonPath();

    }

    @DisplayName("Patch spartan With Map")
    @Test
    public void test2() {
        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "John Doe PATCH");


        //PUT WILL update existing record so we choose one the existing ID, make sure it exists in your IP ADDRESs
        int id = 137;

        JsonPath jsonPath = given().log().body().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBodyMap)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204).extract().jsonPath();


    }

    @DisplayName("DELETE SPARTAN")
    @Test
    public void test3() {
        //we can delete only one time, it will give 204 only one time
        int id = 137;

        given().contentType(ContentType.JSON)
                .pathParam("id", id)
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).extract().jsonPath();


    }


}
