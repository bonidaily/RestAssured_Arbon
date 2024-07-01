package com.cydeo.a_liveRecordings.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_SpartanXMLTest extends SpartanAuthTestBase {
    /**
     * Given accept type is application/xml
     * and basic auth is admin admin
     * When send the request /api/spartans
     * Then status code is 200
     * And content type is application/xml
     * print first spartan name
     * .....
     * ...
     */

    @Test
    public void test1() {
        XmlPath xmlPath = given().accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .body("List.item[1].name", is("Nels"))
                .extract().xmlPath();


        //xmlPath.prettyPrint();
        String thirdName = xmlPath.getString("List.item[2].name");
        System.out.println(thirdName);

        String lastName = xmlPath.getString("List.item[-1].name");
        System.out.println(lastName);

        List<String> allNames = xmlPath.getList("List.item.name");

        System.out.println(allNames);

    }
}
