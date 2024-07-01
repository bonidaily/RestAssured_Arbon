package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class MovieTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://www.omdbapi.com";
    }
}
