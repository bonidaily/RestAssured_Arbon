package com.cydeo.a_liveRecordings.day10;

import com.cydeo.utilities.MovieTestBase;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P02_MovieXml extends MovieTestBase {

    @Test
    public void test1() {
        Response response = given().queryParam("apikey", "81815fe6")
                .queryParam("r", "xml")
                .queryParam("t", "inception")
                .get("");

        XmlPath xmlPath = response.xmlPath();

        xmlPath.prettyPrint();

        String year = xmlPath.get("root.movie.@year");
        System.out.println("year = " + year);

        String movieName = xmlPath.get("root.movie.@title");
        System.out.println("movieName = " + movieName);

        String genre = xmlPath.get("root.movie.@genre");
        System.out.println("genre = " + genre);
    }

    @Test
    public void test2() {
        Response response = given().queryParam("apikey", "81815fe6")
                .queryParam("r", "xml")
                .queryParam("s", "Harry Potter")
                .get("")
                .then()
                .body("root.result.@year", everyItem(greaterThan(String.valueOf(2000))))
                .body("root.@totalResults",is(("140")))
                .extract().response();

        XmlPath xmlPath = response.xmlPath();



        List<String> titles = xmlPath.getList("root.result.@title");

        for (String eachTitle : titles) {
            Assertions.assertTrue(eachTitle.contains("Harry Potter"));
//        }

        }
    }
}