package com.cydeo.homeworkApi.homework04;

import com.cydeo.pojo.homework.task1.Driver;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task01 extends FormulaTestBase {

    /*
    TASK 1 : Solve same task with 4 different way
- Given accept type is json
- And path param driverId is alonso
- When user send request /drivers/{driverId}.json
- Then verify status code is 200
- And content type is application/json; charset=utf-8
- And total is 1
- And givenName is Fernando
- And familyName is Alonso
- And nationality is Spanish
     */
    @Test
    public void jsonPath() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json");

        JsonPath jsonPath = response.jsonPath();


        //- Then verify status code is 200
        assertEquals(200, response.statusCode());

        //- And content type is application/json; charset=utf-8
        assertEquals("application/json; charset=utf-8", response.getContentType());

        //- And total is 1
        int total = jsonPath.getInt("MRData.total");
        System.out.println("total = " + total);
        assertEquals(1, jsonPath.getInt("MRData.total"));

        //- And givenName is Fernando
        String givenName = jsonPath.getString("MRData.DriverTable.Drivers[0].givenName");
        System.out.println("givenName = " + givenName);
        assertEquals("Fernando", jsonPath.getString("MRData.DriverTable.Drivers[0].givenName"));

        //- And familyName is Alonso
        String familyName = jsonPath.getString("MRData.DriverTable.Drivers[0].familyName");
        System.out.println("familyName = " + familyName);
        assertEquals("Alonso", jsonPath.getString("MRData.DriverTable.Drivers[0].familyName"));

        //- And nationality is Spanish
        String nationality = jsonPath.getString("MRData.DriverTable.Drivers[0].nationality");
        System.out.println("nationality = " + nationality);
        assertEquals("Spanish", jsonPath.getString("MRData.DriverTable.Drivers[0].nationality"));


    }

    @Test
    public void hamcrest() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is("1"))
                .body("MRData.DriverTable.Drivers[0].givenName", is("Fernando"))
                .body("MRData.DriverTable.Drivers[0].familyName", is("Alonso"))
                .body("MRData.DriverTable.Drivers[0].nationality", is("Spanish")).extract().jsonPath();

        System.out.println("jsonPath.getString(\"MRData.DriverTable.Drivers[0].nationality\") = " + jsonPath.getString("MRData.DriverTable.Drivers[0].nationality"));
    }

    @Test
    public void javaStructure() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8").extract().jsonPath();

        Map<String, Object> driverInfo = jsonPath.getMap("MRData");
        System.out.println(driverInfo);

        //- And total is 1
        int total = Integer.parseInt((String) driverInfo.get("total"));
        System.out.println("total = " + total);

        //- And givenName is Fernando
        Map<String, Object> driverMap = jsonPath.getMap("MRData.DriverTable.Drivers[0]");
        System.out.println("driverMap.get(\"givenName\") = " + driverMap.get("givenName"));

        //- And familyName is Alonso
        System.out.println("driverMap.get(\"familyName\") = " + driverMap.get("familyName"));

        //- And nationality is Spanish
        System.out.println("driverMap.get(\"nationality\") = " + driverMap.get("nationality"));
    }

    @Test
    public void pojoTask() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8").extract().jsonPath();

        jsonPath.prettyPrint();

        Driver driver = jsonPath.getObject("", Driver.class);
        System.out.println("driver.getMrData().getTotal() = " + driver.getMrData().getTotal());

        //- And givenName is Fernando
        System.out.println("driver.getMrData().getDrivers().getDrivers().get(0).getGivenName() = " + driver.getMrData().getDrivers().getDrivers().get(0).getGivenName());
        //- And familyName is Alonso
        System.out.println("driver.getMrData().getDrivers().getDrivers().get(0).getFamilyName() = " + driver.getMrData().getDrivers().getDrivers().get(0).getFamilyName());
        //- And nationality is Spanish
        System.out.println("driver.getMrData().getDrivers().getDrivers().get(0).getNationality() = " + driver.getMrData().getDrivers().getDrivers().get(0).getNationality());


    }
}
