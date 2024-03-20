package br.com.sicredi.productstests.products.client;

import br.com.sicredi.productstests.products.dto.ProductDto;
import br.com.sicredi.productstests.products.dto.ProductsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import java.io.IOException;
import java.util.List;

import static br.com.sicredi.productstests.commons.Constants.ROOT_URL;
import static br.com.sicredi.productstests.exceptions.ApiExceptionTreament.throwIOException;

public class ProductsClient {

    private OkHttpClient client;
    private ObjectMapper mapper;

    public ProductsClient() {
        this.mapper = new ObjectMapper();
        this.client = new OkHttpClient();
    }

    public List<ProductDto> getAllProducts() throws IOException {
        var request = new Request.Builder()
                .url(ROOT_URL + "/products")
                .build();
        try(var response = this.client.newCall(request).execute()) {
            if(!response.isSuccessful()) throwIOException(response);
            var dtoResponse = this.mapper.readValue(response.body().string(), ProductsDto.class);
            return dtoResponse.products();
        }
    }

    public ProductDto getProduct(Integer productId) throws IOException{
        var request = new Request.Builder()
                .url(ROOT_URL + "/products/" + productId )
                .build();
        try(var response = this.client.newCall(request).execute()) {
            if(!response.isSuccessful()) throwIOException(response);
            var dtoResponse = this.mapper.readValue(response.body().string(), ProductDto.class);
            return dtoResponse;
        }
    }

    public List<ProductDto> getAuthProducts(String jwtToken) throws IOException {
        var request = new Request.Builder()
                .header("Authorization", "Bearer " + jwtToken)
                .url(ROOT_URL + "/auth/products" )
                .build();
        try(var response = this.client.newCall(request).execute()) {
            if(!response.isSuccessful()) throwIOException(response);
            var dtoResponse = this.mapper.readValue(response.body().string(), ProductsDto.class);
            return dtoResponse.products();
        }
    }

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        var requestBody = this.mapper.writeValueAsString(productDto);
        var request = new Request.Builder()
                .url(ROOT_URL + "/products/add")
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .build();
        try(var response = this.client.newCall(request).execute()) {
            if(!response.isSuccessful()) throwIOException(response);
            System.out.println(response.code());
            var responseBody = this.mapper.readValue(response.body().string(), ProductDto.class);
            return responseBody;
        }
    }
}
