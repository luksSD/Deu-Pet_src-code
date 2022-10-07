package br.dp.web.service.impl;

import br.dp.model.Instituicao;
import br.dp.web.service.InstitutionService;
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

        final String endpoint = "http://localhost:8085/api/v1/instituicao/read-all";

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
        final String endpoint = "http://localhost:8085/api/v1/instituicao/read-by-id/" + id;

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
        return false;
    }

    @Override
    public boolean delete(final Long id) {
        return false;
    }

    @Override
    public Long create(final Instituicao entity) {

        Long id = Long.valueOf(-1);

        final String endpoint = "http://localhost:8085/api/v1/instituicao/create";

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
