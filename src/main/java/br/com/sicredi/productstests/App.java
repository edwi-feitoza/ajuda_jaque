package br.com.sicredi.productstests;

import br.com.sicredi.productstests.auth.client.AuthClient;
import br.com.sicredi.productstests.auth.dto.TokenRequestDto;
import br.com.sicredi.productstests.healthcheck.client.HealthCheckVerifier;
import br.com.sicredi.productstests.users.client.UsersClient;
import br.com.sicredi.productstests.users.dto.UserDto;

import java.io.IOException;
import java.util.Random;

public class App
{
    public static void main( String[] args ) throws IOException {
        var healthCheck = new HealthCheckVerifier();
        healthCheck.verifyHealthCheck();

        var usersClient = new UsersClient();
        usersClient.getAllUsers();

        var user = usersClient.getUser(getRandomUserId());
        var authClient = new AuthClient();
        var jwtResponse= authClient.getJwtToken(getUserCredentials(user));
        System.out.println(jwtResponse);
    }

    private static TokenRequestDto getUserCredentials(UserDto user) {
        return new TokenRequestDto(user.username(), user.password(), Short.valueOf("5"));
    }

    private static Integer getRandomUserId() {
        var random = new Random();
        return random.nextInt(101);
    }
}
