package br.com.sicredi.productstests.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Hair(

        @JsonProperty("color")
        String color,

        @JsonProperty("type")
        String type
) {}
