package com.cydeo.a_liveRecordings.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P02_SpartanSpecTest extends SpartanNewTestBase {

    @Test
    public void getAllSpartans() {
        given().log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void getAllSpartansWithReqResSpec() {

        given().spec(reqSpecAdmin)
                .when()
                .get("/spartans")
                .then().spec(responseSpec);

    }

    @Test
    public void getOneSpartanAsAdmin() {

        given().spec(reqSpecAdmin)
                .pathParam("id",10)
                .when()
                .get("/spartans/{id}")
                .then().spec(responseSpec)
                .body("id",is(10));

    }

    @Test
    public void getOneSpartanAsUser() {

        given().spec(reqSpecUser)
                .pathParam("id",10)
                .when()
                .get("/spartans/{id}")
                .then().spec(responseSpec)
                .body("id",is(10));

    }

    @Test
    public void getOneSpartanWithDynamicMethod() {

        given().spec(dynamicReqSpec("admin","admin"))
                .pathParam("id",10)
                .when()
                .get("/spartans/{id}")
                .then().spec(dynamicRespSpec(200))
                .body("id",is(10));

    }

    //Create GET_RBAC.csv
    //username,password,id,statuscode
    //admin,admin,3,200
    //editor,editor,3,200
    //user,user,3,200
    //CREATE A PARAMETERIZED TEST TO CHECK RBAC FOR GET METHOD

    @ParameterizedTest
    @CsvFileSource(resources = "/GetRbac.csv",numLinesToSkip = 1)
    public void getRbac(String username,String password,int id,int statusCode){
        given().spec(dynamicReqSpec(username,password))
                .pathParam("id",id)
                .when()
                .get("/spartans/{id}")
                .then().spec(dynamicRespSpec(statusCode));
    }

    //Create a DELETE_RBAC.csv
    //username,password,id,statusCode
    //editor,editor,3,403
    //user,user,3,403
    //admin,admin,3,204

    //create a parametarized test to check rbac to check delete method

    @ParameterizedTest
    @CsvFileSource(resources = "/DeleteRbac.csv",numLinesToSkip = 1)
    public void deleteRbac(String username,String password,int id,int statusCode){
        given().spec(dynamicReqSpec(username,password))
                .pathParam("id",id)
                .when()
                .delete("/spartans/{id}")
                .then().spec(dynamicRespSpec(statusCode));
    }





}
