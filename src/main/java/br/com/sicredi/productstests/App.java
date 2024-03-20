package br.com.sicredi.productstests;

import br.com.sicredi.productstests.healthcheck.client.HealthCheckVerifier;
import br.com.sicredi.productstests.users.client.UsersClient;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        var healthCheck = new HealthCheckVerifier();
        healthCheck.verifyHealthCheck();

        var usersClient = new UsersClient();
        usersClient.getAllUsers();
    }
}
