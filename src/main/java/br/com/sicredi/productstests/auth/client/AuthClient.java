package br.com.sicredi.productstests.auth.client;

import br.com.sicredi.productstests.auth.dto.TokenRequestDto;
import br.com.sicredi.productstests.auth.dto.TokenResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import java.io.IOException;

import static br.com.sicredi.productstests.exceptions.ApiExceptionTreament.throwIOException;

public class AuthClient {
    private static final String ROOT_URL = "https://dummyjson.com";

    private OkHttpClient client;
    private ObjectMapper mapper;

    public AuthClient() {
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
    }

    public String getJwtToken(TokenRequestDto requestDto) throws IOException {
        var requestBody = this.mapper.writeValueAsString(requestDto);
        var request = new Request.Builder()
                .url(ROOT_URL + "/auth/login")
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .build();
        try(var response = this.client.newCall(request).execute()) {
            if(!response.isSuccessful()) throwIOException(response);
            var responseBody = this.mapper.readValue(response.body().string(), TokenResponseDto.class);
            System.out.println(responseBody);
            return responseBody.token();
        }
    }
}
