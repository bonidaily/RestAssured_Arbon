package com.cydeo.b_liveSessions.liveSessions.week1;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SimpleGetRequest extends HrTestBase {

    /**
     * 1. Send request to HR ORD url and save the response
     * 2. GET /regions
     * 3. Store the response in Response Object that comes from GET Request
     * 4. Print out followings
     * - Headers
     * - Content-Type
     * - Status Code
     * - Response
     * - Date
     * - Verify response body has "Europe"
     * - Verify response has Date
     */

    @Test
    public void simpleGet() {
        Response response = given().accept(ContentType.JSON).when().get("/regions");
        //response.prettyPrint();

        // Print out followings
        // Headers
        System.out.println(response.headers());

        // Content-Type
        System.out.println("response.getContentType() = " + response.getContentType());

        // Status Code
        System.out.println("response.getStatusCode() = " + response.getStatusCode());


        // Date
        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));

        // Verify response body has "Europe"
        assertTrue(response.asString().contains("Europe"));

        // Verify response has Date
        boolean hasDate = response.headers().hasHeaderWithName("Date");
        assertTrue(hasDate);

    }
}
