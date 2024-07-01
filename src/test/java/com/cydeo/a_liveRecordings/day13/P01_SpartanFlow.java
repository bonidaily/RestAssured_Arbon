package com.cydeo.a_liveRecordings.day13;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class P01_SpartanFlow extends SpartanTestBase {

    /*
    /*

  Create a Spartan Flow to run below testCases dynamically

 - POST     /api/spartans
          Create a spartan Map,Spartan class
              name = "API Flow POST"
              gender="Male"
              phone=1231231231l

          - verify status code 201
          - message is "A Spartan is Born!"
          - take spartanID from response and save as a global variable

   */
    static int spartanId;
    static Spartan spartanPost;
    static Spartan spartanPut;



    @DisplayName("Create a spartan")
    @Test
    @Order(1)
    public void postSpartan() {
        spartanPost = new Spartan();
        spartanPost.setName("API Flow POST");
        spartanPost.setGender("Male");
        spartanPost.setPhone(1231231231l);

        log.info("POST SPARTAN --->" +spartanPost );

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanPost)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!")).extract().jsonPath();

        spartanId = jsonPath.getInt("data.id");
        log.info(spartanId + " is created");

    }


    /*

        - GET  Spartan with spartanID     /api/spartans/{id}
            - verify status code 200
            - verify name is API Flow POST

    */

    @DisplayName("Get Spartan with spartan id")
    @Test
    @Order(2)
    public void getSpartan() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", spartanId)
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .body("name", is(spartanPost.getName())).extract().response();

        log.info("GET SPARTAN --> " + response.asString());
    }


    /*

    - PUT  Spartan with spartanID    /api/spartans/{id}

             Create a spartan Map
                name = "API PUT Flow"
                gender="Female"
                phone=3213213213l

             - verify status code 204
*/


    @DisplayName("PUT spartan with spartain ID")
    @Test
    @Order(3)
    public void putSpartan() {
        spartanPut = new Spartan();
        spartanPut.setName("API PUT Flow");
        spartanPut.setGender("Female");
        spartanPut.setPhone(3213213213l);

        log.info("PUT SPARTAN --->" + spartanPut);

        given().log().body().contentType(ContentType.JSON)
                .pathParam("id", spartanId)
                .body(spartanPut)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);

        log.info(spartanId + "---> is updated");


    }

    /*

    - GET  Spartan with spartanID     /api/spartans/{id}


             - verify status code 200
             - verify name is API PUT Flow
    */


    @DisplayName("Get new spartan with spartan id")
    @Test
    @Order(4)
    public void getNewSpartan() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", spartanId)
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .body("name", is(spartanPut.getName())).extract().response();

        log.info("GET SPARTAN --->" + response.asString());
    }


     /*

    - DELETE  Spartan with spartanID   /api/spartans/{id}

             - verify status code 204

     */


    @DisplayName("Delete spartan with spartan Id")
    @Test
    @Order(5)
    public void deleteSpartan() {

        given().contentType(ContentType.JSON)
                .pathParam("id", spartanId)
                .delete("api/spartans/{id}")
                .then()
                .statusCode(204);

        log.info(spartanId +" is deleted");

    }



         /*
     - GET  Spartan with spartanID   /api/spartans/{id}

        verify status code 404

          */


    @DisplayName("Get Spartan Id")
    @Test
    @Order(6)
    public void getSpartann() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", spartanId)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(404).extract().response();

        log.info("GET SPARTAN IS NOT FOUND" + response.asString());

        log.info(spartanId + " is not exist");

    }

}
