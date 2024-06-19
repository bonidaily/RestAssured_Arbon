package com.cydeo.pojo;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown =true)

public class Search {

    private List<Spartan> content;
    private int totalElement;


}
