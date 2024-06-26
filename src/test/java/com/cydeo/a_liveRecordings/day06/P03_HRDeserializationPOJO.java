package com.cydeo.a_liveRecordings.day06;

import com.cydeo.pojo.*;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class P03_HRDeserializationPOJO extends HrTestBase {

    @DisplayName("GET regions to desserializate to POJO - LOMBOK -JSON PROPERTY")
    @Test
    public void test1() {
        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        //get first region from items array and convert it to Region class
        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1 = " + region1);

//        System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
//        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());

        System.out.println("region1.getRegionName() = " + region1.getRegionName());
        System.out.println("region1.getRegionId() = " + region1.getRegionId());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }

    @DisplayName("GET employee to deserialization to POJO with only required fields")
    @Test
    public void test2() {

        JsonPath jsonPath = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath();

        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);

        System.out.println("employee1 = " + employee1);


    }




        /*
    TASK

    Given accept is application/json
    When send request  to /regions endpoint
    Then status should be 200
            verify that region ids are 1,2,3,4
            verify that regions names Europe ,Americas , Asia, Middle East and Africa
            verify that count is 4
        -- Create Regions POJO
        -- And ignore field that you dont need


     */

    @Test
    public void test3() {
        Response response = get("/regions").then()
                .statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();

        Regions reg = jsonPath.getObject("", Regions.class);
        List<Items> items = reg.getItems();

        //Create a list to store region ids
        List<Integer>regionIDs = new ArrayList<>();

        for (Items item : items) {
           regionIDs.add(item.getRegionId());
        }
        System.out.println("regionIDs = " + regionIDs);

        //Create a list to store region name
        List<String>regionNames = new ArrayList<>();
        for (Items eachRegion : items) {
            regionNames.add(eachRegion.getRegionName());
        }
        System.out.println("regionNames = " + regionNames);

        //get count
        System.out.println("reg.getCount() = " + reg.getCount());


//        Search search = jsonPath.getObject("", Search.class);
//        List<Regions> regions = search.getItems();
//
//        System.out.println("search.getCount() = " + search.getCount());
//
//        for (Regions eachRegion : regions) {
//            System.out.print(eachRegion.getRegionId() + " ");
//        }
//
//        for (Regions eachRegion : regions) {
//            System.out.print(eachRegion.getRegionNames() + " ");
//        }



    }
}
