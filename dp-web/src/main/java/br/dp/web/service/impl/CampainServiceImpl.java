package br.dp.web.service.impl;

import br.dp.model.CampainsArquives;
import br.dp.model.Campanha;
import br.dp.web.service.CampainService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CampainServiceImpl implements CampainService {
    @Override
    public List<Campanha> readAll() {

        final String endpoint = "http://localhost:8085/api/v1/campanha/read-all";

        List<Campanha> response = null;

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<String>("");
            final ResponseEntity<Campanha[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, Campanha[].class);

            response = Arrays.asList(requestResponse.getBody());

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public Long create(final Campanha entity) {
        Long id = Long.valueOf(-1);
        final String endpoint = "http://localhost:8085/api/v1/campanha/create";

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Campanha> httpEntity = new HttpEntity<>(entity);
            final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Long.class);
            id = responseEntity.getBody();
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    @Override
    public Campanha readById(final Long id) {

        final String endpoint = "http://localhost:8085/api/v1/campanha/read-by-id/" + id;

        Campanha response = null;

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<String>("");
            final ResponseEntity<Campanha> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, Campanha.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public boolean update(final Campanha entity) {

        final String endpoint = "http://localhost:8085/api/v1/campanha/update";

        boolean response = false;

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Campanha> httpEntity = new HttpEntity<Campanha>(entity);
            final ResponseEntity<Boolean> requestResponse = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity, Boolean.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public boolean deleteById(final Long id) {
        boolean response = false;

        final String endpoint = "http://localhost:8085/api/v1/campanha/delete/" + id;

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
    public CampainsArquives loadCampainImg(final Long id) {
        final String endpoint = "http://localhost:8085/api/v1/campanha/load-images/" + id;

        CampainsArquives response = null;
        try {

            final RestTemplate restTemplate = new RestTemplate();

            final HttpEntity<String> httpEntity = new HttpEntity<String>("");

            final ResponseEntity<CampainsArquives> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                CampainsArquives.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public Long saveFileAttributes(final CampainsArquives campainImg) {
        Long id = Long.valueOf(-1);

        final String endpoint = "http://localhost:8085/api/v1/campanha/save-image";

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<CampainsArquives> httpEntity = new HttpEntity<>(campainImg);
            final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                Long.class);

            id = responseEntity.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}
