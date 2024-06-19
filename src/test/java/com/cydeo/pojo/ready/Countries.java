
package com.cydeo.pojo.ready;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "items",
    "hasMore",
    "limit",
    "offset",
    "count",
    "links"
})
@Data

public class Countries {

    @JsonProperty("items")
    public List<Country> countries;
    @JsonProperty("hasMore")
    public Boolean hasMore;
    @JsonProperty("limit")
    public Integer limit;
    @JsonProperty("offset")
    public Integer offset;
    @JsonProperty("count")
    public Integer count;
    @JsonProperty("links")
    public List<Link__1> links;

}
