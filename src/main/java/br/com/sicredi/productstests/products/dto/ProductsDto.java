package br.com.sicredi.productstests.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProductsDto(

        @JsonProperty("products")
        List<ProductDto> product
) {}
