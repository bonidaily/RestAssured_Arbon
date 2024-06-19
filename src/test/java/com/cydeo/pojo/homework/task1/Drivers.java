package com.cydeo.pojo.homework.task1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Drivers {
    @JsonProperty("givenName")
    private String givenName;

    @JsonProperty("familyName")
    private String familyName;

    @JsonProperty("nationality")
    private String nationality;

}
