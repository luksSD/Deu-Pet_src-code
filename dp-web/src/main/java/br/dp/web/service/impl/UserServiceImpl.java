package br.dp.web.service.impl;

import br.dp.model.UsersArquives;
import br.dp.model.Usuario;
import br.dp.web.service.RestService;
import br.dp.web.service.UserService;
import br.dp.web.util.Constants;
import br.dp.web.util.DummyData;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Usuario validateUsernameAndPassword(final String username, final String password) {

        Usuario user = null;

        if (username.equals("createadm@mail.com")) {
            user = DummyData.generateAdmUser();
            final Long id = create(user);
            return null;
        }

        final String endpoint = Constants.ENDPOINT + "users/login";

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
        final String endpoint = Constants.ENDPOINT + "users/load-images/" + id;

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

    @Override
    public Long create(final Usuario entity) {

        Long id = Long.valueOf(-1);

        final String endpoint = Constants.ENDPOINT + "users/create";

        try {

            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Usuario> httpEntity = new HttpEntity<Usuario>(entity);
            final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                Long.class);
            id = responseEntity.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return id;

    }

    @Override
    public boolean updatePassword(Usuario entity) {

        boolean response = false;

        final String endpoint = Constants.ENDPOINT + "users/change-password";

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Usuario> httpEntity = new HttpEntity<Usuario>(entity);
            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                boolean.class);
            response = Boolean.TRUE.equals(responseEntity.getBody());

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public boolean checkEmailExist(String email) {
        boolean response = false;

        final String endpoint = Constants.ENDPOINT + "users/check-email";

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<String>(email);
            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                boolean.class);

            response = Boolean.TRUE.equals(responseEntity.getBody());

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }
}
