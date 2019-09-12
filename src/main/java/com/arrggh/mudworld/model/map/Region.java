package com.arrggh.mudworld.model.map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Region.RegionBuilder.class)
public class Region {
    private final long id;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RegionBuilder {
    }
}
