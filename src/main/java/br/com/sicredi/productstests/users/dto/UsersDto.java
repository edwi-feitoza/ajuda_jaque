package br.com.sicredi.productstests.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UsersDto(
        @JsonProperty("users")
        List<UserDto> users,

        @JsonProperty("total")
        Short total,

        @JsonProperty("skip")
        Short skip,

        @JsonProperty("limit")
        Short limit
) {}
