package com.cydeo.a_liveRecordings.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class P03_ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    public void test1(int num){
        System.out.println(num);
        Assertions.assertTrue(num>0);

    }

    @ParameterizedTest(name = "{index} - verify name is {0}")
    @ValueSource(strings = {"Mike","Rose","Steven","TJ","Harold","Severus","Sherlock"})
    public void test2(String name){
        System.out.println("name = " + name);
        Assertions.assertTrue(name.length()>4);

    }

    /*
    //TASK
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200
     */

    @ParameterizedTest()
    @ValueSource(ints = {22030,22031,22032,22033,22034,22035,22036})
    public void test3(int zipcode){
        given().log().uri().accept(ContentType.JSON)
                .pathParam("zipcode",zipcode)
                .when()
                .get("https://api.zippopotam.us/us/{zipcode}").prettyPeek()
                .then()
                .statusCode(200);
    }

    @ParameterizedTest
    @CsvSource({"1,2,3",
                "2,3,6",
                "4,5,9"})
    public void test(int num1,int num2,int sum){
        System.out.println(num1 + "+" + num2+ "sum" +sum);

        Assertions.assertEquals(sum,(num1+num2));
        Assertions.assertEquals(sum,(num1+num2));

    }

    /*
     //TASK
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200
     */

    @ParameterizedTest
    @CsvSource({""})
    public void test1(){

    }
}
