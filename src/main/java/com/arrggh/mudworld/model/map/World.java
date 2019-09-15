package com.arrggh.mudworld.model.map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = World.WorldBuilder.class)
public class World {
    private final long id;

    @JsonPOJOBuilder(withPrefix = "")
    public static class WorldBuilder {
    }
}
