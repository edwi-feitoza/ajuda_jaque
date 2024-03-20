package br.com.sicredi.productstests.users.dto;

public record Bank(
        String cardExpire,
        String cardNumber,
        String cardType,
        String currency,
        String iban
) {}
