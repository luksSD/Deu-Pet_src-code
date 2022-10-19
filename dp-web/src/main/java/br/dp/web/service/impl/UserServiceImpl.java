package br.dp.web.service.impl;

import br.dp.model.UsersArquives;
import br.dp.model.Usuario;
import br.dp.web.service.RestService;
import br.dp.web.service.UserService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Usuario validateUsernameAndPassword(final String username, final String password) {

        Usuario user = null;

        final String endpoint = "http://localhost:8085/api/v1/users/login";

        final RestTemplate restTemplate = new RestTemplate();

        try {
            final HttpHeaders httpHeaders = RestService.getAuthenticationHeaders(username, password);
            final HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
            final ResponseEntity<Usuario> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Usuario.class);

            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                return null;
            }

            user = responseEntity.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return user;

    }

    @Override
    public Usuario validateLogin(final String encodedData) {
        return null;
    }


    @Override
    public UsersArquives loadUserImg(final Long id) {
        final String endpoint = "http://localhost:8085/api/v1/users/load-images/" + id;

        UsersArquives response = null;
        try {

            final RestTemplate restTemplate = new RestTemplate();

            final HttpEntity<String> httpEntity = new HttpEntity<String>("");

            final ResponseEntity<UsersArquives> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                UsersArquives.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }
}
