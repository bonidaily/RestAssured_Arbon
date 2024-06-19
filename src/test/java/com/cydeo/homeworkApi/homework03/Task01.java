package com.cydeo.homeworkApi.homework03;

import com.cydeo.utilities.ZippopotamTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Task01 extends ZippopotamTestBase {
    //Given Accept application/json
    //And path zipcode is 22031
    //When I send a GET request to /us endpoint
    //Then status code must be 200
    //And content type must be application/json
    //And Server header is cloudflare
    //And Report-To header exists
    //And body should contains following information
    //post code is 22031
    //country is United States
    //country abbreviation is US
    //place name is Fairfax state is Virginia

    @Test
    void test1() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("zipcode", 22031)
                .get("/us/{zipcode}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server", is("cloudflare"))
                .header("Report-to", is(notNullValue()))
                .body("country", is("United States"))
                .body("'post code'", equalTo("22031"))
                .body("'country abbreviation'",is("US"))
                .body("places[0].state", is("Virginia"))
                .extract().jsonPath();
        jsonPath.prettyPeek();

        int anInt = jsonPath.getInt("'post code'");
        System.out.println(anInt);

//        Map<String,Object>stateVirginia = jsonPath.getMap("");
//
//        String postCode = (String) stateVirginia.get("post code");
//        assertThat(postCode,is("22031"));
//
//        String countryAbbreviation = (String) stateVirginia.get("country abbreviation");
//        System.out.println(countryAbbreviation);
//


//




    }

}
