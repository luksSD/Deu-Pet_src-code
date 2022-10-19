package br.dp.web.service;

import org.springframework.http.HttpHeaders;

import java.util.Base64;

public class RestService {

    public static HttpHeaders getAuthenticationHeaders(final String username, final String password) {

        final String auth = "Username=" + username + ";Password=" + password;

        try {
            final byte[] encondedBytes = Base64.getEncoder().encode(auth.getBytes("utf-8"));

            System.out.println("Dados em formato base64: " + new String(encondedBytes));

            final String header = "Basic " + new String(encondedBytes);

            final HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", header);

            return headers;

        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


}
