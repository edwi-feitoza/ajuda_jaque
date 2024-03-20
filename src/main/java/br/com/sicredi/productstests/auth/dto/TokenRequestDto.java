package br.com.sicredi.productstests.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenRequestDto(
        @JsonProperty("username")
        String username,

        @JsonProperty("password")
        String password,

        @JsonProperty("expiresInMins")
        Short expiresInMins
) {}
