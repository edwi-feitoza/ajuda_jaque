package br.com.sicredi.productstests.dto.user;

public record Bank(
        String cardExpire,
        String cardNumber,
        String cardType,
        String currency,
        String iban
) {}
