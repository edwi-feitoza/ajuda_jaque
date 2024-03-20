package br.com.sicredi.productstests;

import br.com.sicredi.productstests.healthcheck.client.HealthCheckVerifier;
import br.com.sicredi.productstests.users.client.UsersClient;

import java.io.IOException;
import java.util.Random;

public class App
{
    public static void main( String[] args ) throws IOException {
        var healthCheck = new HealthCheckVerifier();
        healthCheck.verifyHealthCheck();

        var usersClient = new UsersClient();
        usersClient.getAllUsers();
        usersClient.getUser(getRandomUserId());
    }

    private static Integer getRandomUserId() {
        var random = new Random();
        return random.nextInt(101);
    }
}
