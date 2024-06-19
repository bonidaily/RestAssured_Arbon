package com.cydeo.homeworkApi.homework02;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class Task03 extends HrTestBase {
    //- Given accept type is Json
    //- Query param value q={“region_id":3}
    //- When users sends request to /countries
    //- Then status code is 200
    //- And all regions_id is 3
    //- And count is 6
    //- And hasMore is false
    //- And Country_name are;
    //Australia,China,India,Japan,Malaysia,Singapore


    @Test
    void task1() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams("q", "{\"region_id\":3}")
                .and()
                .when()
                .get("/countries");

        //response.prettyPrint();

        ////- Then status code is 200
        assertEquals(200,response.getStatusCode());

        //    //- And all regions_id is 3
        ArrayList<Integer> regionID = response.path("items.region_id");
        for (Integer eachId : regionID) {
            assertEquals(3,eachId);
        }

        //    //- And count is 6
        int count = response.path("count");
        assertEquals(6, count);

        //    //- And hasMore is false
        boolean hasMore = response.path("hasMore");
        assertFalse(hasMore);

        //    //- And Country_name are;
        //    //Australia,China,India,Japan,Malaysia,Singapore
        ArrayList<String> expectedCountryNames = new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));
        ArrayList<String> actualcountryNames = response.path("items.country_name");
        System.out.println(expectedCountryNames);
        System.out.println(actualcountryNames);
        assertEquals(expectedCountryNames,actualcountryNames);

        }
    }

