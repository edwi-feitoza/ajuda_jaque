package br.com.sicredi.productstests.dto.user;


import java.time.LocalDate;

public record UserDto (
        Integer id,
        String firstName,
        String lastName,
        String maidenName,
        Short age,
        String gender,
        String email,
        String phone,
        String username,
        String password,
        LocalDate birthDate,
        String Image,
        String bloodGroup,
        Short height,
        Double weight,
        String eyeColor,
        Hair hair,
        String domain,
        String ip,
        Address address,
        String macAddress,
        String university,
        Bank bank,
        Company company,
        String ein,
        String ssn,
        String userAgent,
        Crypto crypto
){}


