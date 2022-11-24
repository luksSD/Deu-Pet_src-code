package br.dp.web.service.impl;

import br.dp.model.Animal;
import br.dp.model.AnimalsArquives;
import br.dp.web.service.AnimalService;
import br.dp.web.util.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AnimalServiceImpl implements AnimalService {


    @Override
    public List<Animal> readAll() {


        final String endpoint = Constants.ENDPOINT + "animal/read-all";

        List<Animal> response = null;
        try {

            final RestTemplate restTemplate = new RestTemplate();

            final HttpEntity<String> httpEntity = new HttpEntity<String>("");

            final ResponseEntity<Animal[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                Animal[].class);

            response = Arrays.asList(Objects.requireNonNull(requestResponse.getBody()));

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public Animal readById(final Long id) {
        final String endpoint = Constants.ENDPOINT + "animal/read-by-id/" + id;

        Animal response = null;

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<String>("");
            final ResponseEntity<Animal> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                Animal.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public Long create(final Animal entity) {
        Long id = Long.valueOf(-1);

        final String endpoint = Constants.ENDPOINT + "animal/create";

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Animal> httpEntity = new HttpEntity<>(entity);
            final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                Long.class);

            id = responseEntity.getBody();

        } catch (final Exception e) {

            System.out.println(e.getMessage());
        }

        return id;
    }

    @Override
    public boolean update(final Animal entity) {
        boolean response = false;

        final String endpoint = Constants.ENDPOINT + "animal/update";

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Animal> httpEntity = new HttpEntity<Animal>(entity);
            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity,
                Boolean.class);

            response = responseEntity.getBody();

        } catch (final Exception e) {

            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public boolean delete(final Long id) {
        boolean response = false;

        final String endpoint = Constants.ENDPOINT + "animal/delete/" + id;

        try {

            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<String>("");
            final ResponseEntity<Boolean> requestResponse = restTemplate.exchange(endpoint, HttpMethod.DELETE,
                httpEntity, Boolean.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {

            System.out.println(e.getMessage());
        }

        return response;
    }

}
