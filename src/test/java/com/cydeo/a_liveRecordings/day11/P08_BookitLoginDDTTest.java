package com.cydeo.a_liveRecordings.day11;

import com.cydeo.utilities.BookitTestBase;
import com.cydeo.utilities.BookitTestBaseQA3;
import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P08_BookitLoginDDTTest extends BookitTestBaseQA3
{

    //create a method to read bookitqa3 excel file information(qa3sheet)
    //use those information as an email and password to send a get request to /sign endpoint
    //verify you got 200 for each request
    //print access token for each request

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void test1(Map<String ,String> userInfo){

        String accessToken = given().accept(ContentType.JSON)

                .queryParam("email", userInfo.get("email"))
                .queryParam("password", userInfo.get("password"))
                .when()
                .get("/sign")
                .then()
                .statusCode(200).extract().jsonPath().getString("accessToken");

        System.out.println(accessToken);



    }


    public static List<Map<String ,String>> getExcelData(){
        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");
        return excelUtil.getDataList();
    }
}
