package br.com.sicredi.productstests.dto.products;

import java.util.List;

public record ProductDto(
        Integer id,
        String title,
        String description,
        Integer price,
        Double discountPercentage,
        Double rating,
        Integer stock,
        String brand,
        String category,
        String thumbnail,
        List<String> images
) {}
