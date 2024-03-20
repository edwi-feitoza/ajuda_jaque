package br.com.sicredi.productstests.dto.products;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProductsDto(

        @JsonProperty("products")
        List<ProductDto> product
) {}
