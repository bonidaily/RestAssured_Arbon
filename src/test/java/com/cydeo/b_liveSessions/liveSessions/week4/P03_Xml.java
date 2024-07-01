package com.cydeo.b_liveSessions.liveSessions.week4;

import com.cydeo.pojo.DriverXML;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class P03_Xml extends FormulaTestBase {
    /*
    /*
          - Given accept type is application/xml
          - When user sends GET request to "/drivers" endpoint
          - Then status code should be 200
                 - first Driver Given Name
                 - all Drivers Given Name
                 - print out first driver driverId attribute
                 - print out all driver driverId attribute
                 - Print out all driver given name if their nationality is Italian

                 - Challenge --> Retrieve all Driver information as a POJO
                             --> Pure JAVA

     */
     @Test
    public void test1(){

         Response response = RestAssured.when().get("/drivers")
                 .then()
                 .statusCode(200)
                 .extract().response();

         XmlPath xmlPath = response.xmlPath();


                //-         first Driver Given Name
         String firstDriverName = xmlPath.getString("MRData.DriverTable.Driver[0].GivenName");
         System.out.println("firstDriverName = " + firstDriverName);

         //                 - all Drivers Given Name
         List<String> allGivenName = xmlPath.getList("MRData.DriverTable.Driver.GivenName");
         System.out.println(allGivenName);

         //                 - print out first driver driverId attribute
         String firstDriverId = xmlPath.getString("MRData.DriverTable.Driver[0].@driverId");
         System.out.println("firstDriverId = " + firstDriverId);

         //                 - print out all driver driverId attribute
         List<Object> allDriversId = xmlPath.getList("MRData.DriverTable.Driver.@driverId");
         System.out.println(allDriversId);

         //                 - Print out all driver given name if their nationality is Italian

         List<String> italainDriversName = xmlPath.getList("MRData.DriverTable.Driver.findAll {it.Nationality=='Italian'}.GivenName");
         System.out.println("italainDriversName = " + italainDriversName);

         //how many drivers i have
         int totalSize = allGivenName.size();

         List<DriverXML>allDrivers = new ArrayList<>();

         for (int i = 0; i < totalSize; i++) {

              DriverXML driver = new DriverXML();

         String driverId = xmlPath.getString("MRData.DriverTable.Driver["+ i +"].@driverId");
         driver.setDriverId(driverId);

         String givenName = xmlPath.getString("MRData.DriverTable.Driver[ "+ i +" ].GivenName");
         driver.setGivenName(givenName);

         String familyName = xmlPath.getString("MRData.DriverTable.Driver[ "+ i +" ].FamilyName");
              driver.setFamilyName(familyName);


         String nationality = xmlPath.getString("MRData.DriverTable.Driver["+ i +"].Nationality");
         driver.setNationality(nationality);

         allDrivers.add(driver);
         
         }

          for (DriverXML eachDriver : allDrivers) {
               System.out.println("eachDriver = " + eachDriver);
          }


     }
}
