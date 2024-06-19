package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class CydeoTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://107.23.92.222:8080";
    }
}
