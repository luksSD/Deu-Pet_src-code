package br.dp.web.service.impl;

import br.dp.model.Instituicao;
import br.dp.model.UsersArquives;
import br.dp.web.service.InstitutionService;
import br.dp.web.util.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Override
    public List<Instituicao> readAll() {

        final String endpoint = Constants.ENDPOINT + "instituicao/read-all";

        List<Instituicao> response = null;
        try {

            final RestTemplate restTemplate = new RestTemplate();

            final HttpEntity<String> httpEntity = new HttpEntity<String>("");

            final ResponseEntity<Instituicao[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                Instituicao[].class);

            response = Arrays.asList(requestResponse.getBody());

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public Instituicao readById(final Long id) {
        final String endpoint = Constants.ENDPOINT + "instituicao/read-by-id/" + id;

        Instituicao response = null;

        try {
            final RestTemplate restTemplate = new RestTemplate();

            final HttpEntity<String> httpEntity = new HttpEntity<String>("");

            final ResponseEntity<Instituicao> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                Instituicao.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public boolean update(final Instituicao entity) {
        boolean response = false;

        final String endpoint = Constants.ENDPOINT + "instituicao/update";

        try {
            final RestTemplate restTemplate = new RestTemplate();

            final HttpEntity<Instituicao> httpEntity = new HttpEntity<Instituicao>(entity);

            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity,
                Boolean.class);

            response = responseEntity.getBody();

        } catch (final Exception e) {

            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public boolean deleteById(final Long id) {
        boolean response = false;

        final String endpoint = Constants.ENDPOINT + "instituicao/delete/" + id;

        try {

            final RestTemplate restTemplate = new RestTemplate();

            final HttpEntity<String> httpEntity = new HttpEntity<String>("");

            final ResponseEntity<Boolean> requestResponse = restTemplate.exchange(endpoint, HttpMethod.DELETE, httpEntity, Boolean.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public Long create(final Instituicao entity) {

        Long id = Long.valueOf(-1);

        final String endpoint = Constants.ENDPOINT + "instituicao/create";

        try {

            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Instituicao> httpEntity = new HttpEntity<Instituicao>(entity);
            final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                Long.class);
            id = responseEntity.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return id;

    }

}
