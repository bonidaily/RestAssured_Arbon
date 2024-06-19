package com.cydeo.homeworkApi.homework03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TaskFromRecording extends HrTestBase {
    /*
    response status code is 200
    content type /json
    get the second city with JsonPath
    get the last city with jsonpath
    get all country ids
    get all city where their country id is uk
     */

    @Test
    public void test(){
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .get("/locations")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("items[1].city", is("Bern"))
                .body("items[-2].city", is("Venice")).extract().jsonPath();

        List<String> list = jsonPath.getList("items.country_id");
        System.out.println(list);

        List<String> cities = jsonPath.getList("items.findAll {it.country_id=='UK'}.city");
        System.out.println(cities);


    }
}
