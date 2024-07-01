package com.cydeo.b_liveSessions.liveSessions.week3;

import com.cydeo.pojo.CategoryPojo;
import com.cydeo.pojo.Product;
import com.cydeo.utilities.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P01_DeserializationPOJO extends FakeStoreTestBase {
    /**
     * Send request to FakeStoreTestBase url and save the response
     * Accept application/json
     * Path Param id is 2
     * Query Param limit 10
     * Query Param offset 0
     * GET /categories/{id}/products
     * Store the response in Response Object that comes from get Request
     * Print out followings
     * - Print response
     * - Content-Type is application/json
     * - Status Code is 200
     * - Retrieve data as JAVA Collections and print out following information
     *
     * System.out.println("====== GET ALL PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
     */

    @Test
    public void testDeserializationPOJO() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .queryParam("limit", 10)
                .queryParam("offset", 0)
                .get("/categories/{id}/products")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.withCharset("utf-8")).extract().jsonPath();

        List<Product> allProducts = jsonPath.getList("", Product.class);

        System.out.println("====== GET ALL PRODUCTS ======");
        for (Product product : allProducts) {
           //System.out.println(product);
        }

        System.out.println("====== GET SECOND PRODUCTS ======");

        Product secondProduct = allProducts.get(1);
        System.out.println(secondProduct);

        System.out.println("====== GET SECOND PRODUCTS PRICE ======");
        int secondProductPrice = secondProduct.getPrice();
        System.out.println("secondProductPrice = " + secondProductPrice);

        System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
        List<String> allImages = secondProduct.getAllImages();
        for (String eachImage : allImages) {
            System.out.println("eachImage = " + eachImage);
            int statusCode = RestAssured.get(eachImage).statusCode();
            Assertions.assertEquals(statusCode,200);
        }

        System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
        String firstImage = allImages.get(0);
        System.out.println("firstImage = " + firstImage);

        System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
        CategoryPojo category = secondProduct.getCategory();
        System.out.println("category = " + category);

        System.out.println("=========get second category category name ");
        System.out.println("category.getName() = " + category.getName());


    }
}
