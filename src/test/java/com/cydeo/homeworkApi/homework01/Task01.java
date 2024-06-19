package com.cydeo.homeworkApi.homework01;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class Task01 extends HrTestBase {

    //- Given accept type is Json
    //- When users sends request to /countries/US
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response contains United States of America
    @DisplayName("Display country id")

    @Test
    public void task1(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParams("country_id","US")
                .when()
                .get("countries/{country_id}");

        //response.prettyPrint();

        // //- Then status code is 200
        assertEquals(200,response.getStatusCode());

        //    //- And Content - Type is application/json
        assertEquals(ContentType.JSON.toString(),response.getContentType());

        //    //- And response contains United States of America
        assertTrue(response.body().asString().contains("United States of America"));


    }
}
