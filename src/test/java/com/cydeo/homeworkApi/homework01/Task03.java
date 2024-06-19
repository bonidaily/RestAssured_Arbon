package com.cydeo.homeworkApi.homework01;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task03 extends HrTestBase {
    //- Given accept type is Json
    //- When users sends request to /regions/1
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response contains Europe
    //- And header should contains Date
    //- And Transfer-Encoding should be chunked


    @Test
    void test03() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("region_id", 1)
                .when()
                .get("regions/{region_id}");

        //response.prettyPrint();

        // Then status code is 200
        assertEquals(200,response.getStatusCode());

        // And Content - Type is application/json
        assertEquals(ContentType.JSON.toString(),response.getContentType());

        //And response contains Europe
        assertTrue(response.body().asString().contains("Europe"));

        // And header should contains Date
        assertTrue(response.headers().hasHeaderWithName("Date"));


        // And Transfer-Encoding should be chunked
        String transferEncoding = response.getHeader("Transfer-Encoding");
        assertEquals("chunked",transferEncoding);
    }
}
