package com.cydeo.pojo.homework.task2;

import com.cydeo.pojo.homework.task1.MRData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructorPojo {

    @JsonProperty("MRData")
    private MRData mrData;

}
