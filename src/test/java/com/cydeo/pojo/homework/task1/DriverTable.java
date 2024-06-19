package com.cydeo.pojo.homework.task1;

import com.cydeo.pojo.homework.task1.Drivers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class DriverTable {

    @JsonProperty("Drivers")
    private List<Drivers> drivers;




}
