package br.com.sicredi.productstests.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProductDto(
        @JsonProperty("id")
        Integer id,

        @JsonProperty("title")
        String title,

        @JsonProperty("description")
        String description,

        @JsonProperty("price")
        Integer price,

        @JsonProperty("discountPercentage")
        Double discountPercentage,

        @JsonProperty("rating")
        Double rating,

        @JsonProperty("stock")
        Integer stock,

        @JsonProperty("brand")
        String brand,

        @JsonProperty("category")
        String category,

        @JsonProperty("thumbnail")
        String thumbnail,

        @JsonProperty("images")
        List<String> images
) {}
