package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BookitTestBaseQA3 {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://api.qa3.bookit.cydeo.com";


    }
}
