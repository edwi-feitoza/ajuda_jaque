package br.com.sicredi.productstests.dto.user;

public record Company(
        Address address,
        String department,
        String name,
        String title
) {
}
