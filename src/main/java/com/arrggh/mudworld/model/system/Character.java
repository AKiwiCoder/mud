package com.arrggh.mudworld.model.system;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Character.CharacterBuilder.class)
public class Character {
    private final long id;
    private final long userId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CharacterBuilder {
    }
}
