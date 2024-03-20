package br.com.sicredi.productstests.users.dto;

public record Address(
        String address,
        String city,
        Coordinates coordinates,
        String postalCode,
        String state
) {}
