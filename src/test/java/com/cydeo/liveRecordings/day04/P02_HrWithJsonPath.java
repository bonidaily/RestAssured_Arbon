package com.cydeo.liveRecordings.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_HrWithJsonPath extends HrTestBase {

    @DisplayName("GET all /countries")
    @Test
    public void test1(){
        Response response = get("/countries");

        //response.prettyPrint();
        //verify status code
        assertEquals(200,response.statusCode());

        //create jsonpath object
        JsonPath jsonPath = response.jsonPath();

        //get me 3rd country name
        System.out.println("jsonPath.getString(\"items[2].country_name\") = " + jsonPath.getString("items[2].country_name"));

        //get me 3rd and 4th country name
        System.out.println("jsonPath.getString(\"items[2,3].country_name\") = " + jsonPath.getString("items[2,3].country_name"));

        //get me all country name where region_id is 2
        List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("list = " + list);

    }


     /*
    Given accept type is application/json
    And query param limit is 200
    When user send request /employees
    Then user should see ............

     */

    @DisplayName("GET all /employees?limit=200 with JsonPath")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("limit", 200)
                .when().get("/employees");

        //response.prettyPrint();

        //assert status code
        assertEquals(200,response.statusCode());
        //create jsonPath object
        JsonPath jsonPath = response.jsonPath();

        //get all emails from response
        List<String> allEmails = jsonPath.getList("items.email");
        System.out.println("allEmails = " + allEmails);
        System.out.println("allEmails.size() = " + allEmails.size());

        //get all emails who is working as IT_PROG
        List<String> emailsIT = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        System.out.println("emailsIT = " + emailsIT);

        //get me all employees first names whose salary is more than 10000
        List<Object> allEmpSalaryMoreThan10k = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("allEmpSalaryMoreThan10k = " + allEmpSalaryMoreThan10k);

        //get me all information from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}\") = " + jsonPath.getString("items.max {it.salary}"));
        //get me first name from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}.first_name\") = " + jsonPath.getString("items.max {it.salary}.first_name"));

        //get me firstname from response who has min salary
        System.out.println("jsonPath.getString(\"items.min {it.salary}.first_name\") = " + jsonPath.getString("items.min {it.salary}.first_name"));


    }

        /*

    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

      */

}
