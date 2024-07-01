package com.cydeo.c_shortsApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class P17_ValueSource {

    @ParameterizedTest
    @ValueSource(strings = {"Muhtar","Saim","Gurhan","Aysun","George"})
    public void test1(String name){
        System.out.println(name);

        Assertions.assertTrue(name.length()>3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,3,6,9,12})
    public void getSpartan(int id){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("http://3.208.19.164:8000/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200).extract().response();

        int actualId = response.path("id");

        Assertions.assertEquals(id,actualId);
    }
}
