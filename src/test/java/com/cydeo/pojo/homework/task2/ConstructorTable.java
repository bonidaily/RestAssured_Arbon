package com.cydeo.pojo.homework.task2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructorTable {

    @JsonProperty("Constructors")
    private List<Constructors> constructorsList;

}
