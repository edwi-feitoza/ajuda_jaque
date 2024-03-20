package br.com.sicredi.productstests.test.products;

import br.com.sicredi.productstests.products.dto.ProductDto;
import br.com.sicredi.productstests.test.utils.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.io.IOException;

import static br.com.sicredi.productstests.commons.Constants.ROOT_URL;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ProductsTest {

    private TokenManager tokenManager;
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        this.tokenManager = new TokenManager();
        this.mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("This test aims to get all available products in dummy's API. Should return HTTP status code 200")
    public void shouldGetAllProductsSuccessfully() {
        when()
                .get(ROOT_URL + "/products")
                .then()
                .statusCode(200)
                .assertThat()
                .body("products", hasItem(
                        allOf(
                                hasEntry("id", 1)
                        )
                ))
                .and()
                .body("total", equalTo(100));
    }

    @Test
    @DisplayName("This test aims to get a single available product in dummy's API. Should return HTTP status code 200")
    public void shouldGetSingleProductSuccessfully() {
        when()
                .get(ROOT_URL + "/products/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .and()
                .body("title", equalTo("iPhone 9"))
                .and()
                .body("description", equalTo("An apple mobile which is nothing like apple"))
                .and()
                .body("brand", equalTo("Apple"));
    }

    @Test
    @DisplayName("This test aims to get a single available product in dummy's API. Should fail because provided ID does not exists")
    public void shouldFailOnGetSingleProductDueToInvalidId() {
        when()
                .get(ROOT_URL + "/products/121")
                .then()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Product with id '121' not found"));
    }

    @Test
    @DisplayName("This test aims to get all available product in dummy's API being authenticated. Should return HTTP status code 200")
    public void shouldGetAllProductsAuthenticatedSuccessfully() {

        var jwtToken = this.tokenManager.generateLongExpirationJwtToken();

        given()
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .get(ROOT_URL + "/auth/products")
                .then()
                .statusCode(200)
                .and()
                .body("total", equalTo(100));
    }

    @Test
    @DisplayName("This test aims to get all available product in dummy's API being authenticated. Should fail due to invalid token provided")
    public void shouldFailOnGetAllProductsDueToInvalidToken() {
        given()
                .header("Authorization", "invalid")
                .when()
                .get(ROOT_URL + "/auth/products")
                .then()
                .statusCode(401)
                .and()
                .body("name", equalTo("JsonWebTokenError"))
                .and()
                .body("message", equalTo("Invalid/Expired Token!"));
    }

    @Test
    @Timeout(120)
    @DisplayName("This test aims to get all available product in dummy's API being authenticated. Should fail due to expired token provided")
    public void shouldFailOnGetAllProductsDueToExpiredToken() throws InterruptedException{
        var jwtToken = this.tokenManager.generateShortExpirationJwtToken();
        Thread.sleep(70000);
        given()
                .header("Authorization", "Bearer " + jwtToken)
                .when()
                .get(ROOT_URL + "/auth/products")
                .then()
                .statusCode(401)
                .and()
                .body("name", equalTo("TokenExpiredError"))
                .and()
                .body("message", equalTo("Token Expired!"));
    }

    @Test
    @DisplayName("This test aims to create a new product by executing a POST on API.")
    public void shouldCreateNewProductOnApiSuccessfully() throws IOException{
        var productToCreate = this.generateNewProduct();
        var requestBody = this.mapper.writeValueAsString(productToCreate);

        given()
                .contentType("application/json")
                .body(requestBody)
                .expect()
                .statusCode(200)
                .and()
                .body("title", equalTo(productToCreate.title()))
                .and()
                .body("brand", equalTo(productToCreate.brand()))
                .when()
                .post(ROOT_URL + "/products/add");
    }

    private ProductDto generateNewProduct() {
        var parameters = new EasyRandomParameters().excludeField(FieldPredicates.named("id"));
        var easyRandom = new EasyRandom(parameters);
        return easyRandom.nextObject(ProductDto.class);
    }
}
