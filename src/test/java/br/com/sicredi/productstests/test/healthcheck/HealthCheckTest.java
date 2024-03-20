package br.com.sicredi.productstests.test.healthcheck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.sicredi.productstests.commons.Constants.ROOT_URL;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HealthCheckTest {


    @Test
    @DisplayName("This test aims to verify the application health check status. Should return http status code 200.")
    public void shouldGetHealthCheckResponseSuccessfully() {
        when()
                .get(ROOT_URL + "/test")
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("ok"))
                .and()
                .body("method", equalTo("GET"));
    }
}
