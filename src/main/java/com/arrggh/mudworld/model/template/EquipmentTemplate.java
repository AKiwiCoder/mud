package com.arrggh.mudworld.model.template;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = EquipmentTemplate.EquipmentTemplateBuilder.class)
public class EquipmentTemplate {
    private final long id;

    @JsonPOJOBuilder(withPrefix = "")
    public static class EquipmentTemplateBuilder {
    }
}
