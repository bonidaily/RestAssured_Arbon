package com.cydeo.homeworkApi.homework01;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Task02 extends HrTestBase {
    //- Given accept type is Json
    //- When users sends request to /employees/1
    //- Then status code is 404

    @Test
    public void tast2(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("employee_id", 1)
                .when()
                .get("employees/{employee_id}");

        //response.prettyPrint();
        System.out.println("response.getStatusCode() = " + response.getStatusCode());



    }
}
