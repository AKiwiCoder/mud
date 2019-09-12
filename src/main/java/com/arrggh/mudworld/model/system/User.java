package com.arrggh.mudworld.model.system;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = User.UserBuilder.class)
public class User {
    private final long id;
    private final String username;
    private final String password;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserBuilder {
    }
}
