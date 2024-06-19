package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://3.208.19.164:7000";
    }

    /*
        Given accept content is "application/json"
        When user sends get request /api/spartans endpoint
        Then user status code should be 200
     */
}
