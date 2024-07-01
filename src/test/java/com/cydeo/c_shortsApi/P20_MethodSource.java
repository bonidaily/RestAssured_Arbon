package com.cydeo.c_shortsApi;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class P20_MethodSource {

    @ParameterizedTest
    @MethodSource("getPracticeData")
    public void test1(Map<String,String>practiceMap){

        System.out.println(practiceMap);

        System.out.println("practiceMap.get(\"firstName\") = " + practiceMap.get("firstName"));
        System.out.println("practiceMap.get(\"lastName\") = " + practiceMap.get("lastName"));
        System.out.println("practiceMap.get(\"email\") = " + practiceMap.get("email"));

    }

    public static List<Map<String,String>>getPracticeData(){
        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/PracticeDDT.xlsx","data");

        return excelUtil.getDataList();
    }
}
