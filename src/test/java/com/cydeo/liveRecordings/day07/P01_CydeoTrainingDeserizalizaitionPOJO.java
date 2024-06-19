package com.cydeo.liveRecordings.day07;

import com.cydeo.pojo.Student;
import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_CydeoTrainingDeserizalizaitionPOJO extends CydeoTestBase {
    @DisplayName("GET /student/2")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");


//


        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        Student student = jsonPath.getObject("students[0]", Student.class);

//      firstName Mark
        System.out.println("student.getFirstName() = " + student.getFirstName());

//      batch 13
        System.out.println("student.getBatch() = " + student.getBatch());

//      major math
        System.out.println("student.getMajor() = " + student.getMajor());

        //emailAddress mark@email.com
        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());


        // companyName Cydeo
        System.out.println("student.getCompany().getCompanyName() = " + student.getCompany().getCompanyName());


//      street 777 5th Ave
        System.out.println("student.getCompany().getAddress().getStreet() = " + student.getCompany().getAddress().getStreet());

//       zipCode 33222
        System.out.println("student.getCompany().getAddress().getZipCode() = " + student.getCompany().getAddress().getZipCode());

    }

    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");


//


        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        com.cydeo.pojo.ready.Student student = jsonPath.getObject("students[0]", com.cydeo.pojo.ready.Student.class);

        System.out.println("student.getJoinDate() = " + student.getJoinDate());

        System.out.println("student.getCompany().getStartDate() = " + student.getCompany().getStartDate());


//
    }
}