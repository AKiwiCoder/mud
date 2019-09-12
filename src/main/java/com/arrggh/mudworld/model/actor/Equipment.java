package com.arrggh.mudworld.model.actor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Equipment.EquipmentBuilder.class)
public class Equipment {
    private final long id;
    private final long templateId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class EquipmentBuilder {
    }
}
