package com.cydeo.homeworkApi.homework02;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class Task01 extends HrTestBase {
    //- Given accept type is Json
    //- Path param value- US
    //- When users sends request to /countries
    //- Then status code is 200
    //- And Content - Type is Json
    //- And country_id is US
    //- And Country_name is United States of America
    //And Region_id is 2


    @Test
    void test01() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("country_id", "US")
                .when()
                .get("countries/{country_id}");

        response.prettyPrint();

        //- Then status code is 200
        assertEquals(200,response.getStatusCode());

        //- And Content - Type is Json
        assertEquals(ContentType.JSON.toString(),response.getContentType());

        //- And country_id is US
        String countryId = response.path("country_id");
        assertEquals("US",countryId);
        System.out.println("countryId = " + countryId);

        //- And Country_name is United States of America
        String countryName = response.path("country_name");
        assertEquals("United States of America",countryName);
        System.out.println("countryName = " + countryName);

        //And Region_id is 2
        int regionId = response.path("region_id");
        assertEquals(regionId,2);
        System.out.println("regionId = " + regionId);
    }
}
