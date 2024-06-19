package com.cydeo.homeworkApi.homework05;

import com.cydeo.pojo.Region;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Task01 extends HrTestBase {

    /*
    —> POST a region then do GET same region to do validations.
Please use Map or POJO class, or JsonPath
Given accept is json
And content type is json
When I send post request to "/regions/" With json:
{
"region_id":100,
"region_name":"Test Region"
}
Then status code is 201
And content type is json
And region_id is 100
And region_name is Test Region
—> GET
Given accept is json
When I send GET request to "/regions/100"
Then status code is 200
And content type is json
And region_id is 100
And region_name is Test Region
     */

    @Test
    public void test1(){
        Region regionPost = new Region();
        regionPost.setRegionName("Test Region");
        regionPost.setRegionId(104);

        System.out.println(regionPost);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(regionPost)
                .when()
                .post("/regions/")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        int id = jsonPath.getInt("region_id");
        System.out.println(id);

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", id)
                .get("/regions/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();

        Region region = response.as(Region.class);

        System.out.println("region.getRegionName() = " + region.getRegionName());
        System.out.println("region.getRegionId() = " + region.getRegionId());
    }
}
