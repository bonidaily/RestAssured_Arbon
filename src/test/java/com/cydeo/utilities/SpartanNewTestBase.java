package com.cydeo.utilities;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class SpartanNewTestBase {
    public static RequestSpecification reqSpecAdmin;
    public static RequestSpecification reqSpecUser;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init(){

       baseURI = "http://3.208.19.164";
       port = 7000;
       basePath="/api";


       //baseUri+port+basePath

        reqSpecAdmin = given().log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        reqSpecUser = given().log().all()
                .accept(ContentType.JSON)
                .auth().basic("user", "user");

        responseSpec = expect().then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification dynamicReqSpec(String username,String password){
        return given().log().all()
                .accept(ContentType.JSON)
                .auth().basic(username, password);
    }

    public static ResponseSpecification dynamicRespSpec(int statusCode){
        return expect().then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON);
    }




}
