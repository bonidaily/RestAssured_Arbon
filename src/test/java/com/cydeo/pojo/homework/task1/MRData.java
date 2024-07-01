package com.cydeo.pojo.homework.task1;

import com.cydeo.pojo.StatusTable;
import com.cydeo.pojo.homework.task1.DriverTable;
import com.cydeo.pojo.homework.task2.ConstructorTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class MRData {

    private int total;

    private int limit;

    @JsonProperty("DriverTable")
    private DriverTable drivers;

    @JsonProperty("ConstructorTable")
    private ConstructorTable constructorTable;

    @JsonProperty("StatusTable")
    private StatusTable statusTable;



}
