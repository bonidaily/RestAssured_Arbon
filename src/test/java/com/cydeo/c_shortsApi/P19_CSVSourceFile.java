package com.cydeo.c_shortsApi;

import com.cydeo.utilities.SpartanTestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class P19_CSVSourceFile extends SpartanTestBase {

    @ParameterizedTest
    @CsvFileSource(resources = "/mockdata.csv", numLinesToSkip = 1)
    public void test1(String name,String gender,long phone){
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);
        System.out.println("-----------------------------------------------------------");

    }

}
