package com.cydeo.homeworkApi.homework02;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task02 extends HrTestBase {
    //- Given accept type is Json
    //- Query param value - q={"department_id":80}
    //- When users sends request to /employees
    //- Then status code is 200
    //- And Content - Type is Json
    //- And all job_ids start with 'SA'
    //- And all department_ids are 80
    //- Count is 25


    @Test
    void test02() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"department_id\":80}")
                .when()
                .get("/employees");

        //response.prettyPrint();

        //- Then status code is 200
        assertEquals(200,response.getStatusCode());

        //- And Content - Type is Json
        assertEquals(ContentType.JSON.toString(),response.getContentType());

        //- And all job_ids start with 'SA'
        ArrayList<String> jobIds = response.path("items.job_id");
        System.out.println(jobIds);
        for (String each : jobIds) {
            assertTrue(each.startsWith("SA"));
        }

        //- And all department_ids are 80
        ArrayList<Integer> departmentId = response.path("items.department_id");
        System.out.println("departmentId = " + departmentId);
        for (Integer eachId : departmentId) {
            assertEquals(80,eachId);
        }

        //- Count is 25
        int count = response.path("count");
        System.out.println("count = " + count);
        assertEquals(25,count);

    }
}
