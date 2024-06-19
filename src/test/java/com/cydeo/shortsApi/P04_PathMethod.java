package com.cydeo.shortsApi;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P04_PathMethod extends SpartanTestBase {
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
    void test1() {
        Response response = given().accept(ContentType.JSON).pathParam("id", "3")
                .get("api/spartans/{id}");

        response.prettyPrint();

        assertEquals(200,response.getStatusCode());

        assertEquals(ContentType.JSON.toString(),response.getContentType());

        int id = response.path("id");

        assertEquals(3,id);

        assertEquals("Fidole",response.path("name"));

        assertEquals("Male",response.path("gender"));

        long phone = response.path("phone");

        assertEquals(6105035231l,phone);

    }

    @DisplayName("Get All Spartans")
    @Test
    public void getAllSpartans(){

        Response response = get("api/spartans");

        response.prettyPrint();

        //get me first spartan id

        int spartanID = response.path("[0].id");

        System.out.println("spartanID = " + spartanID);

        //Get me second spartan name

        String secondName = response.path("[1].name");
        System.out.println("secondId = " + secondName);

//        //Get me last spartan name
        String spartanName = response.path("name[-1]");
        System.out.println("spartanName = " + spartanName);

        //Get me all spartan names
        List<String> allNames = response.path("name");
        System.out.println(allNames);

        //Get me all spartan IDs
        System.out.println("***** IDs *****");
        List<Integer> allIDs=response.path("id");
        System.out.println(allIDs);


    }
}