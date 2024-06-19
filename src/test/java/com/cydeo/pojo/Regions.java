package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Regions {
    //this returns the list of items , then we create a class of items to retrieve the value
    //that we want
    private List<Items> items;
    private int count;

}
