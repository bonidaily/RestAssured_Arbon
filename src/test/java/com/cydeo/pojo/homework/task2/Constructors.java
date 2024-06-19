package com.cydeo.pojo.homework.task2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Constructors {


    private String constructorId;

    private String url;

    private String name;

    private String nationality;

}
