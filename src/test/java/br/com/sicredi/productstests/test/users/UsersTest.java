package br.com.sicredi.productstests.test.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.sicredi.productstests.commons.Constants.ROOT_URL;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class UsersTest {

    @Test
    @DisplayName("This test aims to get all available users in dummy's API. Should return HTTP status code 200")
    public void shoudGetAllUsersSuccessfully() {
        when()
                .get(ROOT_URL + "/users")
                .then()
                .statusCode(200)
                .assertThat()
                .body("users", hasItem(
                        allOf(
                                hasEntry("id", 1)
                        )
                ))
                .and()
                .body("total", equalTo(100));
    }

    @Test
    @DisplayName("This test aims to get a single available user in dummy's API. Should return HTTP status code 200")
    public void shoudGetSingleUsersSuccessfully() {
        when()
                .get(ROOT_URL + "/users/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .and()
                .body("firstName", equalTo("Terry"))
                .and()
                .body("lastName", equalTo("Medhurst"))
                .and()
                .body("email", equalTo("atuny0@sohu.com"));
    }

    @Test
    @DisplayName("This test aims to get a single user available in dummy's API. Should return HTTP status code 404 because the provided user is does not exists.")
    public void shoudFailToGetUserDueToIdNotFound() {
        when()
                .get(ROOT_URL + "/users/200")
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("User with id '200' not found"));

    }
}
