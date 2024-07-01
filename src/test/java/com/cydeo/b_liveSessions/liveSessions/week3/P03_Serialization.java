package com.cydeo.b_liveSessions.liveSessions.week3;

import com.cydeo.utilities.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P03_Serialization extends FakeStoreTestBase {



    @Test
    public void post(){

        Map<String, Object> requestBody = getProduct("Fanta", 4);

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON.withCharset("utf-8")).extract().jsonPath();

        System.out.println("Verify response body title is same as with posted body");
        String actualTitle = jsonPath.getString("title");
        Assertions.assertEquals(requestBody.get("title"),actualTitle);


        System.out.println("Verify response body price is same as with posted price");
        int actualPrice = jsonPath.getInt("price");
        Assertions.assertEquals(requestBody.get("price"),actualPrice);

        System.out.println("Verify response body title is same as with posted body");
        int actualId = jsonPath.getInt("id");
        Assertions.assertNotNull(actualId);

    }



    @Test
    public void put(){

        Map<String, Object> requestBody = getProduct("Put Product", 2);

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .pathParam("id",114)
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.withCharset("utf-8")).extract().jsonPath();

        System.out.println("Verify response body title is same as with posted body");
        String actualTitle = jsonPath.getString("title");
        Assertions.assertEquals(requestBody.get("title"),actualTitle);


        System.out.println("Verify response body price is same as with posted price");
        int actualPrice = jsonPath.getInt("price");
        Assertions.assertEquals(requestBody.get("price"),actualPrice);

    }

    @Test
    public void delete() {


        int id=RestAssured.get("/products").path("id[-1]");
        System.out.println("id = " + id);

        Response response = RestAssured.given()
                .pathParam("id", id)
                .when().delete("/products/{id}").prettyPeek()
                .then().statusCode(200)
                .extract().response();

        String result = response.asString();
        System.out.println("result = " + result);

        Assertions.assertEquals("true",result);

    }

    public Map<String,Object> getProduct(String title,int categoryId){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("title",title);
        dataMap.put("price",10);
        dataMap.put("description","B33");
        dataMap.put("categoryId",categoryId);

        List<String>images = new ArrayList<>();
        images.add("https://i.pinimg.com/280x280_RS/70/d8/64/70d86483986155a4ce2d70d8d288070d.jpg");
        dataMap.put("images",images);

        return dataMap;

    }
}
