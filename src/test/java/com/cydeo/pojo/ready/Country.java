
package com.cydeo.pojo.ready;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "country_id",
    "country_name",
    "region_id",
    "links"
})
@Data
public class Country {

    @JsonProperty("country_id")
    public String countryId;
    @JsonProperty("country_name")
    public String countryName;
    @JsonProperty("region_id")
    public Integer regionId;
    @JsonProperty("links")
    public List<Link> links;

}
