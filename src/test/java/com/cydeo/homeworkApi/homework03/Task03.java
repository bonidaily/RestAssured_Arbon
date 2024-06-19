package com.cydeo.homeworkApi.homework03;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Task03 extends ZippopotamTestBase {
    /*
    TASK 3
Given Accept application/json
And path state is va
And path city is fairfax
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And payload should contains following information
country abbreviation is US
country United States
place name Fairfax
each places must contains fairfax as a value each post code must start with 22
     */

    @Test
    public void test1(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .pathParam("state", "va")
                .pathParam("city", "fairfax")
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("'country abbreviation'", is("US"))
                .body("country", is("United States"))
                .body("'place name'", is("Fairfax"))
                .body("places.'place name'",everyItem(containsString("Fairfax")))
                .body("places.'post code'",everyItem(startsWith("22")))
                .extract().jsonPath();

        jsonPath.prettyPeek();

//        ArrayList<String> listOfPlaces = jsonPath.get("places.'place name'");
//        for (String each : listOfPlaces) {
//            assertThat(each,containsString("Fairfax"));
//        }

        List<Map<String,Object>> list = jsonPath.getList("places");

        for (Map<String, Object> stringObjectMap : list) {
            assertThat(stringObjectMap.get("place name").toString(),containsString("Fairfax"));
        }


    }
}
