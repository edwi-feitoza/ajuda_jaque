package br.com.sicredi.productstests.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ProductsDto(

        @JsonProperty("products")
        List<ProductDto> products,

        @JsonProperty("total")
        Short total,

        @JsonProperty("skip")
        Short skip,

        @JsonProperty("limit")
        Short limit
) {}
