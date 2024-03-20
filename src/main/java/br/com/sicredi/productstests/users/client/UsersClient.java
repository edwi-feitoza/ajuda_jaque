package br.com.sicredi.productstests.users.client;

import br.com.sicredi.productstests.users.dto.UserDto;
import br.com.sicredi.productstests.users.dto.UsersDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.io.IOException;
import java.util.List;

import static br.com.sicredi.productstests.commons.Constants.ROOT_URL;
import static br.com.sicredi.productstests.exceptions.ApiExceptionTreament.throwIOException;

public class UsersClient {
    private OkHttpClient client;
    private ObjectMapper mapper;

    public UsersClient() {
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    public List<UserDto> getAllUsers() throws IOException {
        var request = new Request.Builder()
                .url(ROOT_URL + "/users")
                .build();
        try(var response = this.client.newCall(request).execute()) {
            if(!response.isSuccessful()) throwIOException(response);
            var responseHeaders = response.headers();
            for(Short i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            var dtoResponse = this.mapper.readValue(response.body().string(), UsersDto.class);
            System.out.println(dtoResponse);
            return dtoResponse.users();
        }
    }

    public UserDto getUser(Integer userId) throws IOException{
        var request = new Request.Builder()
                .url(ROOT_URL + "/users/" + userId)
                .build();
        try(var response = this.client.newCall(request).execute()) {
            if(!response.isSuccessful()) throwIOException(response);
            var responseHeaders = response.headers();
            for(Short i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            var dtoResponse = this.mapper.readValue(response.body().string(), UserDto.class);
            System.out.println(dtoResponse);
            return dtoResponse;
        }
    }
}
