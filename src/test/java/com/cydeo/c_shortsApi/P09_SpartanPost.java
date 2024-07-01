package com.cydeo.c_shortsApi;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class P09_SpartanPost extends SpartanTestBase {
    /*
    /*
     Given accept type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John",
     "phone":1234567890
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is A Spartan is Born!
     "name": "John",
     "gender": "Male",
     "phone": 1234567890
     */

    @Test
    public void test1() {
        Spartan spartanPost = new Spartan();
        spartanPost.setGender("Male");
        spartanPost.setName("John");
        spartanPost.setPhone(1234567890);

        System.out.println(spartanPost);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanPost)
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().jsonPath();
        int anInt = jsonPath.getInt("data.id");


        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", anInt)
                .get("/api/spartans/{id}");

        Spartan spartanGet = response.as(Spartan.class);
        Assertions.assertEquals("A Spartan is Born!", jsonPath.getString("success"));
        Assertions.assertEquals(spartanPost.getName(), spartanGet.getName());
        Assertions.assertEquals(spartanPost.getGender(), spartanGet.getGender());
        Assertions.assertEquals(spartanPost.getPhone(), spartanGet.getPhone());


    }

    @Test
    public void test2() {
        Map<String, Object> spartanMap = new LinkedHashMap<>();
        spartanMap.put("name", "John");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1234567890l);

        System.out.println(spartanMap);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanMap)
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        Assertions.assertEquals("A Spartan is Born!", jsonPath.getString("success"));
        Assertions.assertEquals(spartanMap.get("name"), jsonPath.getString("data.name"));
        Assertions.assertEquals(spartanMap.get("gender"), jsonPath.getString("data.gender"));
        Assertions.assertEquals(spartanMap.get("phone"), jsonPath.getLong("data.phone"));


    }

    @Test
    public void spartanAsPOJO() {

        Spartan spBody = new Spartan();
        spBody.setName("John Pojo");
        spBody.setGender("Male");
        spBody.setPhone(3213213213l);

        System.out.println("sp = " + spBody);


        JsonPath jsonPath =
                given().log().body().accept(ContentType.JSON) // API I need response in json
                        .contentType(ContentType.JSON) // API I am sending data in json
                        .body(spBody). // serialization --> JAVA(Collections/POJO) to JSON
                        when().post("/api/spartans").
                        then()
                        .statusCode(201)
                        .contentType("application/json")
                        .extract().jsonPath();

    }
}