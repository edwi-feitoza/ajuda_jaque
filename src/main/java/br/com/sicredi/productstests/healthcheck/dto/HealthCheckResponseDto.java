package br.com.sicredi.productstests.healthcheck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HealthCheckResponseDto(

        @JsonProperty("status")
        String status,

        @JsonProperty("method")
        String method
) {}
