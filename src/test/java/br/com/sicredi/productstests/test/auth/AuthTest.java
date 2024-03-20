package br.com.sicredi.productstests.test.auth;

import br.com.sicredi.productstests.test.utils.TokenManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static br.com.sicredi.productstests.commons.Constants.ROOT_URL;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class AuthTest {

    private TokenManager tokenManager;

    @BeforeEach
    public void setup() throws IOException {
        this.tokenManager = new TokenManager();
    }

    @Test
    @DisplayName("This test aims to get a JWT token successfuly.")
    public void shouldGetJwtAuthTokenSuccessfully() {
        given()
                .contentType("application/json")
                .body(this.tokenManager.generateLongExpirationValidTokenRequest())
                .expect()
                .statusCode(200)
                .when()
                .post(ROOT_URL + "/auth/login");
    }

    @Test
    @DisplayName("This test aims to get a JWT token. Should fail because the credentials provides are invalid.")
    public void shouldFailOnGetJwtAuthTokenDueToInvalidCredentials() {
        given()
                .contentType("application/json")
                .body(this.tokenManager.generateInValidCredentialsTokenRequest())
                .expect()
                .statusCode(400)
                .and()
                .body("message", equalTo("Invalid credentials"))
                .when()
                .post(ROOT_URL + "/auth/login");
    }

    @Test
    @DisplayName("This test aims to get a JWT token. Should fail because the request body is malformed.")
    public void shouldFailOnGetJwtAuthTokenDueToMalformedRequestBody() {
        given()
                .contentType("application/json")
                .body("anything invalid")
                .expect()
                .statusCode(400)
                .and()
                .body("message", equalTo("Unexpected token a in JSON at position 0"))
                .when()
                .post(ROOT_URL + "/auth/login");
    }
}
