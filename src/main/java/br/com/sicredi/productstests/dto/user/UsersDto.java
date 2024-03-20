package br.com.sicredi.productstests.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UsersDto(
        @JsonProperty("users")
        List<UserDto> users
) {}
