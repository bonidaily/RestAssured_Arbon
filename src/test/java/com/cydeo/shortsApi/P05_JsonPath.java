package com.cydeo.shortsApi;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class P05_JsonPath extends SpartanTestBase {
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
    public void getsingleSpartan(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3).
                when().get("/api/spartans/{id}");

        response.prettyPrint();

        //    Then response status code should be 200
        assertEquals(200,response.statusCode());

        //    And response content type is application/json
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //create JSonPath Object

        JsonPath jsonPath = response.jsonPath();

        //         id is 3
        int anInt = jsonPath.getInt("id");
        assertEquals(3,anInt);

        //         name is "Fidole"
        String name = jsonPath.getString("name");
        assertEquals("Fidole",name);

        //         gender is "Male"
        String gender = jsonPath.getString("gender");
        assertEquals("Male",gender);

        //         phone is 6105035231
        long phone = jsonPath.getLong("phone");
        assertEquals(6105035231l,phone);
    }

    @Test
    public void allSpartans(){

        Response response = get("/api/spartans");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        //get me first spartan id
        int anInt = jsonPath.getInt("[0].id");
        System.out.println(anInt);

        //get me second spartan name
        String secondSpartanName = jsonPath.getString("[1].name");
        System.out.println("secondSpartanName = " + secondSpartanName);

        //get me last spartan name
        String lastName = jsonPath.getString("name[-1]");
        System.out.println("lastName = " + lastName);

        //get me all spartan Ids
        List<Integer> listId = jsonPath.getList("id");
        System.out.println("listId = " + listId);

        //get me all spartan names
        List<Object> listNames = jsonPath.getList("name");
        System.out.println("listNames = " + listNames);
    }

}
