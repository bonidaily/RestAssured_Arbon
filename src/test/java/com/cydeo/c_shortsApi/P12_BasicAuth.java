package com.cydeo.c_shortsApi;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P12_BasicAuth extends SpartanAuthTestBase {

    @Test
    public void GetRequestAsGuestUser(){
        given().accept(ContentType.JSON)
                .and()
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(401)
                .body("error",is("Unauthorized"));
    }

    @Test
    public void GetRequestAsUser(){
        given().accept(ContentType.JSON)
                .auth().basic("user","user")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200);
    }

    @Test
    public void GetRequestAsEditor(){
        given().accept(ContentType.JSON)
                .auth().basic("user","user")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteSpartanAsUser(){
        given()
                .auth().basic("user","user")
                .and()
                .pathParam("id",1)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403)
                .body("error",is("Forbidden"));
    }
}
