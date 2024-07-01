package com.cydeo.c_shortsApi;

import com.cydeo.utilities.FormulaTestBase;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P16_XMLPath extends FormulaTestBase {

    @Test
    public void xmlpath(){

        Response response = given().when()
                .get("/circuits").prettyPeek()
                .then()
                .statusCode(200).extract().response();

        //create xmlPath
        XmlPath xmlPath = response.xmlPath();

        //get me firsst circuit name
        String circuitName = xmlPath.getString("MRData.CircuitTable.Circuit[0].CircuitName");
        System.out.println("circuitName = " + circuitName);

        //get me first locality
        String locality = xmlPath.getString("MRData.CircuitTable.Circuit[0].Location.Locality");
        System.out.println("locality = " + locality);

        //get me country
        String country = xmlPath.getString("MRData.CircuitTable.Circuit[0].Location.Country");
        System.out.println("country = " + country);

        //get circuitId

        String circuitId = xmlPath.getString("MRData.CircuitTable.Circuit[0].@circuitId");
        System.out.println("circuitId = " + circuitId);


    }
}
