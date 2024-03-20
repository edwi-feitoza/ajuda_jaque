package br.com.sicredi.productstests.users.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record UserDto (

        @JsonProperty("id")
        Integer id,

        @JsonProperty("firstName")
        String firstName,

        @JsonProperty("lastName")
        String lastName,

        @JsonProperty("maidenName")
        String maidenName,

        @JsonProperty("age")
        Short age,

        @JsonProperty("gender")
        String gender,

        @JsonProperty("email")
        String email,

        @JsonProperty("phone")
        String phone,

        @JsonProperty("username")
        String username,

        @JsonProperty("password")
        String password,

        @JsonProperty("birthDate")
        LocalDate birthDate,

        @JsonProperty("image")
        String image,

        @JsonProperty("bloodGroup")
        String bloodGroup,

        @JsonProperty("height")
        Short height,

        @JsonProperty("weight")
        Double weight,

        @JsonProperty("eyeColor")
        String eyeColor,

        @JsonProperty("hair")
        Hair hair,

        @JsonProperty("domain")
        String domain,

        @JsonProperty("ip")
        String ip,

        @JsonProperty("address")
        Address address,

        @JsonProperty("macAddress")
        String macAddress,

        @JsonProperty("university")
        String university,

        @JsonProperty("bank")
        Bank bank,

        @JsonProperty("company")
        Company company,

        @JsonProperty("ein")
        String ein,

        @JsonProperty("ssn")
        String ssn,

        @JsonProperty("userAgent")
        String userAgent,

        @JsonProperty("crypto")
        Crypto crypto
){}


