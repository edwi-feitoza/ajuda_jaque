package br.com.sicredi.productstests;

import br.com.sicredi.productstests.auth.client.AuthClient;
import br.com.sicredi.productstests.auth.dto.TokenRequestDto;
import br.com.sicredi.productstests.healthcheck.client.HealthCheckVerifier;
import br.com.sicredi.productstests.products.client.ProductsClient;
import br.com.sicredi.productstests.products.dto.ProductDto;
import br.com.sicredi.productstests.users.client.UsersClient;
import br.com.sicredi.productstests.users.dto.UserDto;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class App
{
    public static void main( String[] args ) throws IOException {
        var healthCheck = new HealthCheckVerifier();
        healthCheck.verifyHealthCheck();

        var usersClient = new UsersClient();
        usersClient.getAllUsers();

        var user = usersClient.getUser(getRandomId());
        var authClient = new AuthClient();
        var jwtResponse= authClient.getJwtToken(getUserCredentials(user));
        System.out.println(jwtResponse);

        var productsClient = new ProductsClient();
        var producstsResponde = productsClient.getAllProducts();
        System.out.println(producstsResponde);

        var productResponse = productsClient.getProduct(getRandomId());
        System.out.println(productResponse);

        var createdProductResponse = productsClient.addProduct(createProductToAdd());
        System.out.println(createdProductResponse);

        var authProductResponse = productsClient.getAuthProducts(jwtResponse);
        System.out.println(authProductResponse);

    }

    private static ProductDto createProductToAdd() {
        return new ProductDto(null,
                "Teste",
                "Teste",
                150,
                23.4,
                15.4,
                18,
                "Teste",
                "Teste",
                "Teste",
                Arrays.asList("Teste"));
    }

    private static TokenRequestDto getUserCredentials(UserDto user) {
        return new TokenRequestDto(user.username(), user.password(), Short.valueOf("5"));
    }

    private static Integer getRandomId() {
        var random = new Random();
        return random.nextInt(101);
    }
}
