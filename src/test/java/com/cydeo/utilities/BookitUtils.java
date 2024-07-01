package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class BookitUtils {

    public static String getToken(String email, String password){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .get("/sign")
                .then()
                .statusCode(200).extract().jsonPath();

        String accessToken = jsonPath.getString("accessToken");

        return "Bearer " + accessToken;
    }
    public static String getTokenByRole(String role) {
        String email = "";
        String password = "";

        switch (role) {
            case "teacher":
                email = ConfigurationReader.getProperty("teacher_email");
                password = ConfigurationReader.getProperty("teacher_password");
                break;

            case "student-member":
                email = ConfigurationReader.getProperty("team_member_email");
                password = ConfigurationReader.getProperty("team_member_password");
                break;
            case "student-leader":
                email = ConfigurationReader.getProperty("team_leader_email");
                password = ConfigurationReader.getProperty("team_leader_password");
                break;
            default:

                throw new RuntimeException("Invalid Role Entry :\n>> " + role +" <<");
        }

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        String accessToken = given()
                .queryParams(credentials)
                .when().get( "/sign").path("accessToken");

        return  "Bearer " + accessToken;

    }

    public static RequestSpecification getReqSpec(String role){
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", BookitUtils.getTokenByRole(role));
    }   public static ResponseSpecification getRespSpec(int statusCode){
        return expect().then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON);

    }
}
