package br.com.sicredi.productstests.users.dto;

public record Company(
        Address address,
        String department,
        String name,
        String title
) {
}
