package com.cydeo.a_liveRecordings.day08;

import com.cydeo.utilities.BookitTestBase;
import com.cydeo.utilities.BookitUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P02_BookitTest extends BookitTestBase {
    String accessToken = BookitUtils.getToken("lfinnisz@yolasite.com","lissiefinnis");

    @DisplayName("Get API CAMPUSES")
    @Test
    void test1(){
        given().accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .get("api/campuses").prettyPeek()
                .then()
                .statusCode(200);
        System.out.println("accessToken = " + accessToken);

        //Create new Util class nad it will generate token based on provided email and password
        //BookItUtils.getToken(String email,String password)
    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .header("authorization",BookitUtils.getToken("lfinnisz@yolasite.com","lissiefinnis"))
                .when()
                .get("/api/users/me").prettyPeek()
                .then()
                .statusCode(200).extract().jsonPath();
    }

    @Test
    public void test3(){
        given().accept(ContentType.JSON)
                .auth().oauth2(accessToken)
                .when()
                .get("/api/users/me").prettyPeek()
                .then()
                .statusCode(200).extract().jsonPath();
    }


}
