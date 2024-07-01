package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {
    public Logger log = LogManager.getLogger();
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://3.208.19.164:8000";
    }

    /*
        Given accept content is "application/json"
        When user sends get request /api/spartans endpoint
        Then user status code should be 200
     */
}
