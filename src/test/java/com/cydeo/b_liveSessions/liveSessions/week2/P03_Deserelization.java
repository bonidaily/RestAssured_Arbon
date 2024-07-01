package com.cydeo.b_liveSessions.liveSessions.week2;

import com.cydeo.utilities.FakeStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P03_Deserelization extends FakeStoreTestBase {
    P02_QueryParam p02QueryParam = new P02_QueryParam();
    /*
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
     * - Content-Type is application/json; charset=utf-8
     * - Status Code is 200
     * - Retrieve data as JAVA Collections and print out following information
     * <p>
     * System.out.println("====== GET ALL PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
     */

    @Test
    public void deserelization(){
        JsonPath jsonPath = given().accept(ContentType.JSON).pathParam("id", 2)
                .and()
                .queryParams(P02_QueryParam.createQueryParam(0, 10))
                .get("/categories/{id}/products")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8").extract().jsonPath();

        //jsonPath.prettyPrint();


                System.out.println("====== GET ALL PRODUCTS ======");
        List<Map<String, Object>> allProducts = jsonPath.getList("");
        for (Map<String, Object> stringObjectMap : allProducts) {
            System.out.println(stringObjectMap);
        }
              System.out.println("====== GET SECOND PRODUCTS ======");
        Map<String, Object> secondProduct = jsonPath.getMap("[1]");
        System.out.println(secondProduct);

         System.out.println("====== GET SECOND PRODUCTS PRICE ======");
        int priceSecond = jsonPath.getInt("price[1]");
        System.out.println(priceSecond);

         System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
        List<String> listOfImages = jsonPath.getList("[1].images");
        System.out.println(listOfImages);

       System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
        String image = jsonPath.getString("[1].images[0]");
        System.out.println(image);

        System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
        Map<String, Object> secondProductsCategory = jsonPath.getMap("[1].category");
        System.out.println(secondProductsCategory);

       System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
        String secondProductsCategoryName = jsonPath.getString("[1].category.name");
        System.out.println(secondProductsCategoryName);

        System.out.println("========================JAva Collections=================================================");

        System.out.println("=================== GET second PRODUCTS ===================================================");
        Map<String, Object> secondStringObjectMap = allProducts.get(1);
        System.out.println(secondStringObjectMap);


        //     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
        int price = (int)secondStringObjectMap.get("price");
        System.out.println("price = " + price);

        //     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
        List<String> images = (List<String>)secondStringObjectMap.get("images");
        System.out.println("images = " + images);

        //     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
        String secondImage = images.get(0);
        System.out.println("secondImage = " + secondImage);

        //     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
        Map<String,Object> secondProductsCategoy = (Map<String, Object>) secondStringObjectMap.get("category");
        System.out.println("secondProductsCategoy = " + secondProductsCategoy);
        //     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
        String secondName = (String) secondProductsCategoy.get("name");
        System.out.println("name = " + secondName);


    }
}
