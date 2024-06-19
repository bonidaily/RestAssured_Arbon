package com.cydeo.homeworkApi.homework05;

import com.cydeo.pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Task02 extends HrTestBase {
    /*
    —-> PUT request then DELETE
Given accept type is Json
And content type is json
When I send PUT request to /regions/100 With json body:
{
"region_id": 100,
"region_name": "Wooden Region"
}
Then status code is 200
And content type is json region_id is 100
region_name is Wooden Region
—> DELETE
Given accept type is Json
When I send DELETE request to /regions/100
Then status code is 200
     */
    @Test
    public void test1(){

        Region regionPost = new Region();
        regionPost.setRegionId(50);
        regionPost.setRegionName("Wooden Region");

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(regionPost)
                .when()
                .post("/regions/")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("region_name", is("Wooden Region"))
                .body("region_id", is(50)).extract().jsonPath();

        int id = jsonPath.getInt("region_id");

        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .delete("/regions/{id}")
                .then()
                .statusCode(200);
    }
}
