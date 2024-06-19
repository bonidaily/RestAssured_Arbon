
package com.cydeo.pojo.ready;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rel",
    "href"
})
@Data
public class Link__1 {

    @JsonProperty("rel")
    public String rel;
    @JsonProperty("href")
    public String href;

}
