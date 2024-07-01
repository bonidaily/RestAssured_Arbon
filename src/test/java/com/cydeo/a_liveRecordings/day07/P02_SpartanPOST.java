package com.cydeo.a_liveRecordings.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class P02_SpartanPOST extends SpartanTestBase {
    /*
      /**
     Given accept type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is 'A Spartan is Born!'
     "name": "John Doe",
     "gender": "Male",
     "phone": 8877445596
     */

    @DisplayName("POST Spartan with String body")
    @Test
    public void test1() {
        String requestBody = "{\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John Doe\",\n" +
                "     \"phone\":8877445596\n" +
                "     }";

        given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("John Doe"))
                .body("data.gender", is("Male"))
                .body("data.phone", is(8877445596l)).extract().jsonPath();


    }


    @DisplayName("POST Spartan with Map body")
    @Test
    public void test2() {

        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "John Doe");
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("phone", 8877445596l);
        System.out.println(requestBodyMap);

        given().log().body()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestBodyMap)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("John Doe"))
                .body("data.gender", is("Male"))
                .body("data.phone", is(8877445596l)).extract().jsonPath();


    }

    @DisplayName("POST Spartan with Spartan POJO")
    @Test
    public void test3() {
        Spartan spartan1 = new Spartan();
        spartan1.setName("Harold Finch");
        spartan1.setGender("Male");
        spartan1.setPhone(1234567890l);

        System.out.println(spartan1);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartan1)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!")).extract().jsonPath();

        //request body verification
        assertEquals("Harold Finch", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(1234567890l, jsonPath.getLong("data.phone"));


    }

    @DisplayName("POST Spartan with Spartan POJO")
    @Test
    public void test4() {
        Spartan spartanPost = new Spartan();
        spartanPost.setName("Harold Finch");
        spartanPost.setGender("Male");
        spartanPost.setPhone(1234567890l);

        System.out.println(spartanPost);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartanPost)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!")).extract().jsonPath();

        //request body verification
        assertEquals("Harold Finch", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(1234567890l, jsonPath.getLong("data.phone"));

        int anInt = jsonPath.getInt("data.id");
        System.out.println(anInt);

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",anInt)
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();

        Spartan spartanGet = response.as(Spartan.class);

        Assertions.assertEquals(spartanPost.getName(),spartanGet.getName());



    }
}