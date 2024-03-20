package br.com.sicredi.productstests.test.utils;

import br.com.sicredi.productstests.auth.dto.TokenRequestDto;
import br.com.sicredi.productstests.auth.dto.TokenResponseDto;
import br.com.sicredi.productstests.users.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;

import static br.com.sicredi.productstests.commons.Constants.ROOT_URL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private ObjectMapper mapper;

    public TokenManager() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    public String generateLongExpirationValidTokenRequest() {
        return this.generateTokenRequestDto(Short.valueOf("5"));
    }

    public String generateShortExpirationValidTokenRequest() {
        return this.generateTokenRequestDto(Short.valueOf("1"));
    }

    public String generateInValidCredentialsTokenRequest() {
        var tokenRequestDto = new TokenRequestDto("invalid", "invalid", Short.valueOf("5"));
        try {
            return this.mapper.writeValueAsString(tokenRequestDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateLongExpirationJwtToken() {
        return this.getJwtToken(this.generateLongExpirationValidTokenRequest());
    }

    public String generateShortExpirationJwtToken() {
        return this.getJwtToken(this.generateShortExpirationValidTokenRequest());
    }
    private String getJwtToken(String tokenRequest) {
        baseURI = ROOT_URL + "/auth/login";
        var httpRequest = given()
                .body(tokenRequest)
                .contentType("application/json");
        var response = httpRequest.post();
        var responseBody = response.getBody();
        try {
            var jwtToken = this.mapper.readValue(responseBody.asString(), TokenResponseDto.class);
            return jwtToken.token();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateTokenRequestDto(Short expiration) {
        int userId = (int)(Math.random() * 100) + 1;
        baseURI = ROOT_URL + "/users/" + userId;
        var httpRequest = given();
        var response = httpRequest.get("");
        var responseBody = response.getBody();
        try {
            var user = this.mapper.readValue(responseBody.asString(), UserDto.class);
            var tokenRequestDto = new TokenRequestDto(user.username(), user.password(), expiration);
            return this.mapper.writeValueAsString(tokenRequestDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
