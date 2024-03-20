package br.com.sicredi.productstests.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponseDto(
        @JsonProperty("id")
        Integer id,

        @JsonProperty("username")
        String username,

        @JsonProperty("email")
        String email,

        @JsonProperty("firstName")
        String firstName,

        @JsonProperty("lastName")
        String lastName,

        @JsonProperty("gender")
        String gender,

        @JsonProperty("image")
        String image,

        @JsonProperty("token")
        String token
) {}
