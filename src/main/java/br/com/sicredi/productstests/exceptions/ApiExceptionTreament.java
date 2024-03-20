package br.com.sicredi.productstests.exceptions;

import okhttp3.Response;

import java.io.IOException;

public class ApiExceptionTreament {

    public static void throwIOException(Response response) throws IOException{
        throw new IOException("Unexpected responde code " + response);
    }
}
