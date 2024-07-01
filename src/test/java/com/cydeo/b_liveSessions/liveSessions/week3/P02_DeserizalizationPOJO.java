package com.cydeo.b_liveSessions.liveSessions.week3;

import com.cydeo.pojo.StatusPOJO;
import com.cydeo.pojo.homework.task1.MRData;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class P02_DeserizalizationPOJO extends FormulaTestBase {


        /*
          /*
        - ERGAST API ( Formula API )
        - When user send request /status.json
        - Then verify status code is 200
        - And content type is application/json; charset=utf-8
        - And total is 137
        - And limit is 30
        - And each status has statusId
     */

    @Test
    public void test1(){

        JsonPath jsonPath = given().log().uri()
                .get("/status.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        MRData mrData = jsonPath.getObject("MRData", MRData.class);

        System.out.println(mrData);


    }

    @Test
    void pagination(){

        int limit = 30;
        int offset = 0;
        JsonPath jsonPath = given().log().uri()
                .queryParam("offset",0)
                .queryParam("limit",30)
                .get("/status.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        MRData mrData = jsonPath.getObject("MRData", MRData.class);
        int total = mrData.getTotal();

        //to store all StatusPOJO for multiple executions
        List< StatusPOJO>allStatusPOJO= new ArrayList<>();

        //current execution results
        List<StatusPOJO> status;

        //first page information between 0-30


        while (offset < total){

             jsonPath = given().log().uri()
                    .queryParam("offset",offset)
                    .queryParam("limit",limit)
                    .get("/status.json")
                    .then()
                    .statusCode(200)
                    .contentType("application/json; charset=utf-8")
                    .extract().jsonPath();

            mrData = jsonPath.getObject("MRData", MRData.class);

            status = mrData.getStatusTable().getStatus();

            allStatusPOJO.addAll(status);
            offset = offset + limit;

        }

        System.out.println("allStatusPOJO.size() = " + allStatusPOJO.size());

        //- And each status has statusId
        for (StatusPOJO eachStatus : allStatusPOJO) {
            Assertions.assertNotNull(eachStatus.getStatusId());
        }


    }
}
