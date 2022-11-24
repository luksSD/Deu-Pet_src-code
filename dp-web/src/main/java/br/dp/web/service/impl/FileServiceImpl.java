package br.dp.web.service.impl;

import br.dp.model.AnimalsArquives;
import br.dp.model.CampainsArquives;
import br.dp.model.UsersArquives;
import br.dp.web.service.FileService;
import br.dp.web.util.Constants;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public boolean uploadFiles(List<MultipartFile> files, Long id) {
        boolean response = false;
        String endpoint = Constants.UPLOAD_ANIMAL_FILES_ENDPOINT;

        List<AnimalsArquives> filesList = new ArrayList<AnimalsArquives>();
        boolean primary = true;

        for (MultipartFile file : files) {
            byte[] dataByte;
            String dataString = "";

            try {
                dataByte = file.getBytes();
                dataString = new String(Base64.getEncoder().encode(dataByte));

            } catch (IOException e) {
                e.printStackTrace();
            }

            AnimalsArquives fileToUpload = new AnimalsArquives();
            fileToUpload.setFile(dataString);
            fileToUpload.setAnimalID(id);
            fileToUpload.setType(file.getContentType());
            fileToUpload.setPrimary(false);

            if(primary){
                fileToUpload.setPrimary(true);
                primary = false;
            }

            filesList.add(fileToUpload);
        }

        try {
            final RestTemplate restTemplate = new RestTemplate();
            HttpEntity<List<AnimalsArquives>> httpEntity = new HttpEntity<List<AnimalsArquives>>(filesList);
            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                    Boolean.class);
            response = responseEntity.getBody();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    @Override
    public boolean uploadFile(MultipartFile file, Long id, String type) {

        boolean response = false;
        String endpoint = "";

        byte[] dataByte;
        String dataString = "";

        try {
            dataByte = file.getBytes();
            dataString = new String(Base64.getEncoder().encode(dataByte));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Object fileToUpload;
        if(type.equals("user") || type.equals("INSTITUICAO")) {
            fileToUpload = new UsersArquives();
            ((UsersArquives) fileToUpload).setFile(dataString);
            ((UsersArquives) fileToUpload).setUserId(id);
            ((UsersArquives) fileToUpload).setType(file.getContentType());
            endpoint = Constants.UPLOAD_USER_FILE_ENDPOINT;
        } else{
            fileToUpload = new CampainsArquives();
            ((CampainsArquives) fileToUpload).setFile(dataString);
            ((CampainsArquives) fileToUpload).setCampainId(id);
            ((CampainsArquives) fileToUpload).setType(file.getContentType());
            endpoint = Constants.UPLOAD_CAMPAIN_FILE_ENDPOINT;
        }

        try {
            final RestTemplate restTemplate = new RestTemplate();
            if(type.equals("user") || type.equals("INSTITUICAO")) {
                HttpEntity<UsersArquives> httpEntity = new HttpEntity<UsersArquives>((UsersArquives) fileToUpload);
                final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                    Boolean.class);
                response = responseEntity.getBody();

            } else{
                HttpEntity<CampainsArquives> httpEntity = new HttpEntity<CampainsArquives>((CampainsArquives) fileToUpload);
                final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
                    Boolean.class);
                response = responseEntity.getBody();
            }

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
