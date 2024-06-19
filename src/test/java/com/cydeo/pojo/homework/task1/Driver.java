package com.cydeo.pojo.homework.task1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Driver {

    @JsonProperty("MRData")
    private MRData mrData;
}
