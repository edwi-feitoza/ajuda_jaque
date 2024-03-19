package br.com.sicredi.productstests.dto.user;

public record Address(
        String address,
        String city,
        Coordinates coordinates,
        String postalCode,
        String state
) {}
