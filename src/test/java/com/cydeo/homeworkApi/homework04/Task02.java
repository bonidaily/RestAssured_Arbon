package com.cydeo.homeworkApi.homework04;

import com.cydeo.pojo.homework.task2.ConstructorPojo;
import com.cydeo.pojo.homework.task2.Constructors;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task02 extends FormulaTestBase {
    /*
    - Given accept type is json
- When user send request /constructorStandings/1/constructors.json
- Then verify status code is 200
- And content type is application/json; charset=utf-8
- And total is 17
- And limit is 30
- And each constructor has constructorId
- And constructor has name
- And size of constructor is 17
- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
     */

    @Test
    public void json() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8").extract().jsonPath();

        //- And total is 17
        assertEquals(17, jsonPath.getInt("MRData.total"));

        //- And limit is 30
        assertEquals(30, jsonPath.getInt("MRData.limit"));

        //- And each constructor has constructorId
        List<String> listConstructorId = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
        for (String each : listConstructorId) {
            assertFalse(each.isEmpty());
        }

        //- And constructor has name
        List<String> listConstructorName = jsonPath.getList("MRData.ConstructorTable.Constructors.name");
        for (String each : listConstructorName) {
            assertFalse(each.isEmpty());
        }

        //- And size of constructor is 17
        int size = jsonPath.getInt("MRData.total");
        System.out.println(size);


        //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
        assertThat(listConstructorId, containsInRelativeOrder("benetton", "mercedes", "team_lotus"));
        assertTrue(listConstructorId.contains("mercedes"));


    }

    @Test
    public void hamcrest() {
        given().accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is("17"))
                .body("MRData.limit", is("30"))
                .body("MRData.ConstructorTable.Constructors.constructorId", everyItem(is(notNullValue())))
                .body("MRData.ConstructorTable.Constructors.name", everyItem(is(notNullValue())))
                .body("MRData.ConstructorTable.Constructors", hasSize(17))
                .body("MRData.ConstructorTable.Constructors.constructorId", containsInRelativeOrder("benetton", "mercedes", "team_lotus"));

    }

    @Test
    public void jsonToJava() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8").extract().jsonPath();
        Map<String, Object> map = jsonPath.getMap("MRData");
        //- And total is 17
        String total = (String) map.get("total");
        System.out.println("total = " + total);
        //- And limit is 30
        String limit = (String) map.get("limit");
        System.out.println("limit = " + limit);

        //- And each constructor has constructorId
        List<Map<String, Object>> listConstructors = jsonPath.getList("MRData.ConstructorTable.Constructors");
        for (Map<String, Object> eachContructor : listConstructors) {
            assertFalse(eachContructor.get("constructorId").toString().isEmpty());
        }
        //- And constructor has name
        for (Map<String, Object> eachConstructor : listConstructors) {
            assertFalse(eachConstructor.get("name").toString().isEmpty());
        }
        //- And size of constructor is 17
        int size = listConstructors.size();
        System.out.println("size = " + size);

        //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
        List<String> listConstructorId = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
        assertTrue(listConstructorId.contains("benetton") && listConstructorId.contains("mercedes") && listConstructorId.contains("team_lotus"));


    }

    @Test
    public void pojo() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8").extract().jsonPath();

        ConstructorPojo constructorPojo = jsonPath.getObject("", ConstructorPojo.class);

        System.out.println("constructorPojo.getMrData().getLimit() = " + constructorPojo.getMrData().getLimit());
        System.out.println("constructorPojo.getMrData().getTotal() = " + constructorPojo.getMrData().getTotal());

        //- And each constructor has constructorId
        List<Constructors> constructorsList = constructorPojo.getMrData().getConstructorTable().getConstructorsList();
        for (Constructors eachConstructor : constructorsList) {

            assertTrue(!eachConstructor.getConstructorId().isEmpty());
        }
        //- And constructor has name

        for (Constructors eachConstructor : constructorsList) {
            assertFalse(eachConstructor.getName().isEmpty());
        }

        //- And size of constructor is 17
        int sizeOfConstructors = constructorsList.size();
        assertEquals(17,sizeOfConstructors);
        System.out.println("sizeOfConstructors = " + sizeOfConstructors);

        //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
        List<String>constructorsIDs = new ArrayList<>();

        for (Constructors each : constructorsList) {
            constructorsIDs.add(each.getConstructorId());
        }
        assertThat(constructorsIDs,containsInRelativeOrder("benetton","mercedes","team_lotus"));
    }
}