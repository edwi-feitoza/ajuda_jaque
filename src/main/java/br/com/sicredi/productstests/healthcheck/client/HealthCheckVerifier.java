package br.com.sicredi.productstests.healthcheck.client;

import br.com.sicredi.productstests.healthcheck.dto.HealthCheckResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class HealthCheckVerifier {

    private static final String ROOT_URL = "https://dummyjson.com";
    private OkHttpClient client;
    private ObjectMapper mapper;

    public HealthCheckVerifier() {
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
    }

    public void verifyHealthCheck() throws IOException{
        var request = new Request.Builder()
                .url(ROOT_URL + "/test")
                .build();

        try(var response = this.client.newCall(request).execute()){
            if(!response.isSuccessful()) throw new IOException("Unexpected response code" + response);
            Headers responseHeaders = response.headers();
            for (Short i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            var dtoResponse = this.mapper.readValue(response.body().string(), HealthCheckResponseDto.class);
            System.out.println(dtoResponse);
            System.out.println(response.code());
        }
    }
}
