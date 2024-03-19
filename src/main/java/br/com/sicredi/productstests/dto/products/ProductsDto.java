package br.com.sicredi.productstests.dto.products;

import java.util.List;

public record ProductsDto(
        List<ProductDto> product
) {}
