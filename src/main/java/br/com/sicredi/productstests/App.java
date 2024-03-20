package br.com.sicredi.productstests;

import br.com.sicredi.productstests.healthcheck.HealthCheckVerifier;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        var healthCheck = new HealthCheckVerifier();
        healthCheck.verifyHealthCheck();
    }
}
