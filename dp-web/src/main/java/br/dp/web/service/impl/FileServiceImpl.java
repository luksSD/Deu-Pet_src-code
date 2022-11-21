package br.dp.web.service.impl;

import br.dp.model.UsersArquives;
import br.dp.web.service.FileService;
import br.dp.web.service.RestService;
import br.dp.web.util.Constants;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public boolean uploadFiles(List<MultipartFile> file) {
        return false;
    }

    @Override
    public boolean uploadFile(MultipartFile file, Long id, String type) {

        UsersArquives userFile = new UsersArquives();
        boolean response = false;
        String endpoint = "";

        if(type.equals("user")) {
            endpoint = Constants.UPLOAD_USER_FILE_ENDPOINT;
        } else{
            endpoint = Constants.UPLOAD_CAMPAIN_FILE_ENDPOINT;
        }
        byte[] dataByte;
        String dataString = "";

        try {
            dataByte = file.getBytes();
            dataString = new String(Base64.getEncoder().encode(dataByte));

        } catch (IOException e) {
            e.printStackTrace();
        }

        userFile.setFile(dataString);
        userFile.setUserId(id);
        userFile.setType(file.getContentType());

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<UsersArquives> httpEntity = new HttpEntity<UsersArquives>(userFile);
            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                Boolean.class);
            response = responseEntity.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;

    }

    @Override
    public String downloadUserFile(Long id) {

        String response = null;

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<String>("");
            final ResponseEntity<String> requestResponse = restTemplate.exchange(Constants.DOWNLOAD_USER_FILE_ENDPOINT + id, HttpMethod.GET, httpEntity,
                String.class);

            response = requestResponse.getBody();

            return response;

        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    @Override
    public List<String> downloadAnimalFiles(Long id) {
        List<String> response = null;

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<String>("");
            final ResponseEntity<String[]> requestResponse = restTemplate.exchange(Constants.DOWNLOAD_ANIMAL_FILE_ENDPOINT + id, HttpMethod.GET, httpEntity,
                String[].class);

            response = Arrays.asList(requestResponse.getBody());

            return response;

        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String downloadCampainFile(Long id) {
        String response = null;

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<String>("");
            final ResponseEntity<String> requestResponse = restTemplate.exchange(Constants.DOWLOAD_CAMPAIN_FILE_ENDPOINT + id, HttpMethod.GET, httpEntity,
                String.class);

            response = requestResponse.getBody();

            return response;

        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
