package com.cydeo.homeworkApi.homework05;

import com.cydeo.utilities.DB_Util;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Task03 extends HrTestBase {
    /*
    TASK 3:
—> POST a region then Database validations. Please use
Map
Given accept is json
and content type is json
When I send post request to "/regions/" With json:
{
"region_id":200,
"region_name":"Test Region"
}
Then status code is 201
content type is json
When I connect to HR database and execute query "SELECT
region_id,
region_name FROM regions WHERE region_id = 200"
Then region_name from database should match region_name
//from POST request
//—> DELETE
//Given accept type is Json
When i send DELETE request to /regions/200
Then status code is 200
     */

    @Test
    public void test1(){
        Map<String ,Object> regionMap = new LinkedHashMap<>();
        regionMap.put("region_id",670);
        regionMap.put("region_name","Test Region");

        //POST TO DATABASE
        JsonPath jsonPath = given().log().body().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(regionMap)
                .when()
                .post("/regions/")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON).extract().jsonPath();


        //CREATE CONNECTION TO DATABASE
        String url = "SELECT\n" +
                "    region_id,\n" +
                "    region_name FROM regions WHERE region_id = 670";

        DB_Util.createConnection();
        DB_Util.runQuery(url);

        //RETRIEVE DATA FROM DATA BASE AND FROM API
        int regionIdDB = Integer.parseInt(DB_Util.getFirstRowFirstColumn());
        String regionNameDB = DB_Util.getCellValue(1, 2);
        String regionNameAPI = jsonPath.getString("region_name");
        int regionIdApi = jsonPath.getInt("region_id");
        Assertions.assertEquals(regionIdDB,regionIdApi);
        Assertions.assertEquals(regionNameDB,regionNameAPI);

        //GET THE ID FROM THE REGION THAT POSTED TO API
        int id = jsonPath.getInt("region_id");

        //DELETE THAT REGION WITH GIVEN PATH PARAM ID
        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .delete("/regions/{id}")
                .then()
                .statusCode(200);






    }
}