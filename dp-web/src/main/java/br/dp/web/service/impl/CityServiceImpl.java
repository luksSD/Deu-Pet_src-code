package br.dp.web.service.impl;

import br.dp.model.Municipio;
import br.dp.web.service.CityService;
import br.dp.web.util.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityServiceImpl implements CityService {

    @Override
    public Municipio readById(final Long id) {
        final String endpoint = Constants.ENDPOINT + "city/read-by-id/" + id;

        Municipio response = null;

        try {

            final RestTemplate restTemplate = new RestTemplate();

            final HttpEntity<String> httpEntity = new HttpEntity<String>("");

            final ResponseEntity<Municipio> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                Municipio.class);

            response = requestResponse.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

}
