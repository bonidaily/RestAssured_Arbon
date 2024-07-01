package com.cydeo.b_liveSessions.liveSessions.week3;

import com.cydeo.pojo.Category;
import com.cydeo.utilities.FakeStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class P04_SerializationFLow extends FakeStoreTestBase {
    //CREATE - POST /categories - response -id

    /*
    CREATE CATEGORIES

    Given accept header is application json
    And content type is application json
    And requestBody field and values are
        |name                 |image  |
        |My Category          |url  |
    When I send POST request /categories endpoint
    Then status code should be 201
    And response body should have posted product information
    And id field should exist
    */

    static Integer id;
    static Category category;


    @Order(1)
    @Test
    public void post(){
        category =new Category();
        category.setName("Badge 30");
        category.setImage("https://t4.ftcdn.net/jpg/00/81/38/59/360_F_81385977_wNaDMtgrIj5uU5QEQLcC9UNzkJc57xbu.jpg");

        System.out.println("/post/categories");
        System.out.println(category);

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(category)
                .post("/categories")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON.withCharset("utf-8")).extract().jsonPath();

        //assertion for name
        Assertions.assertEquals(category.getName(),jsonPath.getString("name"));

        //verify id exist
        id = jsonPath.getInt("id");
        System.out.println("Category is generated with " + id);
        Assertions.assertNotNull(id);
    }
    @Order(2)
    @Test
    public void get(){

        System.out.println("Get/Categories/" + id);

        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .get("/categories/{id}")
                .then()
                .statusCode(200)
                .body("name",is(category.getName()));

        System.out.println("Assertions are passed with id " + id);
    }

    @Order(3)
    @Test
    public void delete(){

        System.out.println("DELETE CATEGORIES " + id);

        Response response = given().pathParam("id", id)
                .when()
                .delete("categories/{id}")
                .then()
                .statusCode(200)
                .extract().response();

        Assertions.assertEquals("true",response.asString());

    }

    @Order(4)
    @Test
    public void get2(){
        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .get("categories/{id}")
                .then()
                .statusCode(400)
                .body("name",is("EntityNotFoundError"));
    }


}
