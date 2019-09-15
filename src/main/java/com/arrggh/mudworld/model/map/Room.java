package com.arrggh.mudworld.model.map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Room.RoomBuilder.class)
public class Room {
    private final long id;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RoomBuilder {
    }
}
