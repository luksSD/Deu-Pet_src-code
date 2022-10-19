package br.dp.web.service;

import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RestService {

    public static HttpHeaders getAuthenticationHeaders(final String username, final String password) {

        final String auth = "Username=" + username + ";Password=" + password;

        final byte[] encodedBytes;


        try {

            encodedBytes = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            System.out.println("encoded bytes: " + new String(encodedBytes));

            final String header = "Basic " + new String(encodedBytes);

            final HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", header);

            return headers;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
