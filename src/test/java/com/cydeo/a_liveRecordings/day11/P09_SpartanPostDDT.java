package com.cydeo.a_liveRecordings.day11;

import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P09_SpartanPostDDT extends Spartan {
    /**
     * POST SPARTAN DDT
     * <p>
     * Given content type is json
     * And accept type is json
     * When I POST Spartan
     * And status code needs to 201
     * Verify spartan information matching with dynamic that we provide
     * <p>
     * Generate DUMMY DATA
     * https://www.mockaroo.com/
     * <p>
     * name
     * gender
     * phone
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/mockdata.csv", numLinesToSkip = 1)
    public void test1(String name, String gender, long phone) {
        Spartan spartan = new Spartan();
        spartan.setName(name);
        spartan.setGender(gender);
        spartan.setPhone(phone);

       given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .baseUri("http://3.208.19.164:8000")
                .body(spartan)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .body("data.name", equalTo(spartan.getName()))
                .body("data.gender", equalTo(spartan.getGender()))
                .body("data.phone",equalTo(spartan.getPhone())).extract().jsonPath();



        List<Integer> id = given().accept(ContentType.JSON)
                .baseUri("http://3.208.19.164:8000")
                .queryParam("nameContains", spartan.getName())
                .queryParam("gender", spartan.getGender())
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200).extract().jsonPath().getList("content.id");



        System.out.println(id);
        int size = id.size();

        for(int i = 0; i < size; i++){
            given().pathParam("id",id.get(i))
                    .baseUri("http://3.208.19.164:8000")
                    .delete("api/spartans/{id}")
                    .then()
                    .statusCode(204);
        }


    }

}
