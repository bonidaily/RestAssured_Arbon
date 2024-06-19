package com.cydeo.liveSessions.liveSessions.week2;

import com.cydeo.utilities.FakeStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P02_QueryParam extends FakeStoreTestBase {

    /*
     /**
     * 1- Given accept type is Json
     * 2- Query Parameters value is
     * - limit —> 10
     * - offset —> 0
     * 3- When user sends GET request to /products
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - Each product has id
     * - Each product has category id
     * - Get all product names
     * - Get product ids
     * - Get all category names
     */

    @Test
    void queryParam1(){
        JsonPath jsonPath = given().accept(ContentType.JSON).queryParam("limit", "10")
                .queryParam("offset", "0")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("id", everyItem(is(notNullValue())))
                .extract().jsonPath();

        // * - Get all product title
//        List<Map<String,String>> listOfObjects = jsonPath.getList("");
//        List<String> productTitles = new ArrayList<>();
//        for (Map<String, String> eachMap : listOfObjects) {
//          productTitles.add(eachMap.get("title"));
//        }
//        System.out.println(productTitles);
        List<String> allTitles = jsonPath.getList("title");
        System.out.println(allTitles);

        // * - Get first product title
        String firstProduct = jsonPath.getString("title[0]");
        System.out.println(firstProduct);

        // * - Get last product title
        String lastProduct = jsonPath.getString("title[-1]");
        System.out.println(lastProduct);

        //     * - Get product ids
        List<Integer> listOfId = jsonPath.getList("id");
        System.out.println(listOfId);

        //     * - Get all category names
        List<String> categoryNames = jsonPath.getList("category.name");
        System.out.println(categoryNames);


    }
    @Test
    void queryParam2(){
//        Map<String,Integer> queryParam = new HashMap<>();
//        queryParam.put("offset",0);
//        queryParam.put("limit",100);
//        System.out.println(queryParam);

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParams(createQueryParam(0,100))
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("id", everyItem(is(notNullValue())))
                .extract().jsonPath();
        jsonPath.prettyPeek();


        // * - Get all product title
//        List<Map<String,String>> listOfObjects = jsonPath.getList("");
//        List<String> productTitles = new ArrayList<>();
//        for (Map<String, String> eachMap : listOfObjects) {
//          productTitles.add(eachMap.get("title"));
//        }
//        System.out.println(productTitles);


        // * - Get first product title


        // * - Get last product title


        //     * - Get product ids


        //     * - Get all category names
    }
    public static Map<String,Integer> createQueryParam(int offset,int limit){
        Map<String,Integer> queryParam = new HashMap<>();
        queryParam.put(""+offset+"",0);
        queryParam.put(""+limit+"",100);

        return queryParam;

    }


}
