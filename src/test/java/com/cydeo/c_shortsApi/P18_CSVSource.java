package com.cydeo.c_shortsApi;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P18_CSVSource extends SpartanTestBase {


    @ParameterizedTest
    @CsvSource({"Mike,Male,1231231231",
                "John,Male,1231231231",
               "Steve,Male,1231231231"})
    public void csvSource(String name,String gender,long phone){
        Map<String,Object>body = new HashMap<>();
        body.put("name",name);
        body.put("gender",gender);
        body.put("phone",phone);
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("api/spartans")
                .then()
                .statusCode(201).extract().jsonPath();

        Assertions.assertEquals(name,jsonPath.getString("data.name"));
        Assertions.assertEquals(gender,jsonPath.getString("data.gender"));
        Assertions.assertEquals(phone,jsonPath.getLong("data.phone"));

    }
}
