package com.cydeo.c_shortsApi;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P10_SpartanPUTPATCH extends SpartanTestBase {

    @Test
    void spartanPut() {

        Map<String ,Object> putBody = new HashMap<>();
        putBody.put("name","Rest PUT");
        putBody.put("gender","Male");
        putBody.put("phone",1234567890l);

        System.out.println(putBody);

        given().contentType(ContentType.JSON)
                .and()
                .pathParam("id",176)
                .body(putBody)
                .when()
                .put("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204);
    }

    @Test
    void spartanPatch() {

        Map<String ,Object> patchBody = new HashMap<>();
       patchBody.put("name","Majlind");

        System.out.println(patchBody);

        given().contentType(ContentType.JSON)
                .and()
                .pathParam("id",177)
                .body(patchBody)
                .when()
                .patch("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204);
    }
}
