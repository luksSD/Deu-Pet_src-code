package br.dp.api.service.impl;

import br.dp.api.service.AwsS3Service;
import br.dp.api.util.Constants;
import br.dp.db.dao.AnimalDao;
import br.dp.db.dao.CampanhaDao;
import br.dp.db.dao.InstituicaoDao;
import br.dp.db.dao.UserDao;
import br.dp.model.*;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    @Autowired
    private AmazonS3Client awsS3Client;

    @Autowired
    private InstituicaoDao institutionDao;

    @Autowired
    private AnimalDao animalDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CampanhaDao campainDao;

    @Override
    public String downloadUserFile(Long id){
        return userDao.loadUserImg(id).getPath();
    }

    @Override
    public List<String> downloadAnimalFiles(Long id) {

        List<String> pathList = new ArrayList<String>();
        List<AnimalsArquives> animalFiles = animalDao.loadImages(id);

        for(AnimalsArquives animal : animalFiles){
            pathList.add(animal.getPath());
        }

        return pathList;
    }

    @Override
    public List<AnimalsArquives> downloadAllAnimalsFiles(Long id) {

        List<AnimalsArquives> animalFiles = animalDao.loadImages(id);

        return animalFiles;
    }

    @Override
    public String downloadCampainFile(Long id) {
        return campainDao.loadImage(id).getPath();
    }

    @Override
    public Boolean uploadUserFile(UsersArquives file) {

        Long result = Long.valueOf(-1);

        String existFile = downloadUserFile(file.getUserId());

        boolean updateFile = existFile != null;

        try {
            String[] filenameExtension = file.getType().split("/");
            String key = Constants.USER_KEY + file.getUserId() + "." +filenameExtension[1];
            byte[] data = Base64.getDecoder().decode(file.getFile().getBytes(StandardCharsets.UTF_8));
            InputStream stream = new ByteArrayInputStream(data);

            ObjectMetadata metaData = new ObjectMetadata();
            metaData.setContentType(file.getType());
            metaData.setContentLength(data.length);

            awsS3Client.putObject(Constants.BUCKET_NAME, key, stream , metaData);
            awsS3Client.setObjectAcl(Constants.BUCKET_NAME, key, CannedAccessControlList.PublicRead);

            file.setPath(awsS3Client.getResourceUrl(Constants.BUCKET_NAME, key));
            file.setKey(key);

            if(updateFile) {
                result = (long) (userDao.updateFileAttributes(file) ? 1 : -1);
            } else{
                result = userDao.saveFileAttributes(file);
            }

            if(result != -1){
                return true;
            }

        } catch (SdkClientException e) {
            System.out.println(e.getMessage());
        }

        return false;

    }

    @Override
    public Boolean uploadCampainFile(CampainsArquives file) {

        Long result = Long.valueOf(-1);

        String existFile = downloadCampainFile(file.getCampainId());
        boolean updateFile = existFile != null;

        try {
            String[] filenameExtension = file.getType().split("/");
            String key = Constants.CAMPAIGN_KEY + file.getCampainId() + "." +filenameExtension[1];

            byte[] data = Base64.getDecoder().decode(file.getFile().getBytes(StandardCharsets.UTF_8));
            InputStream stream = new ByteArrayInputStream(data);
            ObjectMetadata metaData = new ObjectMetadata();
            metaData.setContentType(file.getType());
            metaData.setContentLength(data.length);

            awsS3Client.putObject(Constants.BUCKET_NAME, key, stream , metaData);
            awsS3Client.setObjectAcl(Constants.BUCKET_NAME, key, CannedAccessControlList.PublicRead);

            file.setPath(awsS3Client.getResourceUrl(Constants.BUCKET_NAME, key));
            file.setKey(key);

            if(updateFile) {
                result = (long) (campainDao.updateFileAttributes(file) ? 1 : -1);
            } else{
                result = campainDao.saveFileAttributes(file);
            }

            if(result != -1){
                return true;
            }

        } catch (SdkClientException e) {
            System.out.println(e.getMessage());
        }

        return false;

    }

    @Override
    public Boolean uploadAnimalFiles(List<AnimalsArquives> animalFiles) {

        boolean result = false;
        int id = 1;

        List<AnimalsArquives> filesList = downloadAllAnimalsFiles(animalFiles.get(0).getAnimalID());
        if(filesList.size() > 0) {
            deleteAnimalFiles(animalFiles.get(0).getAnimalID());
        }

        try {
            for (AnimalsArquives file: animalFiles) {
                String[] filenameExtension = file.getType().split("/");
                String key = Constants.ANIMAL_KEY + file.getAnimalID() + "/" + id++ + "." + filenameExtension[1];

                byte[] data = Base64.getDecoder().decode(file.getFile().getBytes(StandardCharsets.UTF_8));
                InputStream stream = new ByteArrayInputStream(data);
                ObjectMetadata metaData = new ObjectMetadata();
                metaData.setContentType(file.getType());
                metaData.setContentLength(data.length);

                awsS3Client.putObject(Constants.BUCKET_NAME, key, stream , metaData);
                awsS3Client.setObjectAcl(Constants.BUCKET_NAME, key, CannedAccessControlList.PublicRead);

                file.setPath(awsS3Client.getResourceUrl(Constants.BUCKET_NAME, key));
                file.setKey(key);

            }

            result = animalDao.saveFileAttributes(animalFiles);

        } catch (SdkClientException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public Boolean deleteUserFile(long id) {

        boolean response = true;
        String key = userDao.loadUserImg(id).getKey();

        if(key != null || key.equals("")) {
            awsS3Client.deleteObject(Constants.BUCKET_NAME, key);
            response = userDao.deleteFile(id);
        }

        return response;
    }

    @Override
    public Boolean deleteCampaignFile(long id) {
        boolean response = true;
        String key = campainDao.loadImage(id).getKey();

        if(key != null || key.equals("")) {
            awsS3Client.deleteObject(Constants.BUCKET_NAME, key);
            response = campainDao.deleteFile(id);
        }

        return response;
    }

    @Override
    public Boolean deleteAnimalFiles(long id) {
        boolean response = true;
        List<AnimalsArquives> animalFiles = animalDao.loadImages(id);


        for (AnimalsArquives animal : animalFiles) {
            if(animal.getKey() != null || animal.getKey().equals("")) {
                awsS3Client.deleteObject(Constants.BUCKET_NAME, animal.getKey());
            }
        }
        if(animalFiles.size() > 0) {
            response = animalDao.deleteFiles(id);
        }

        return response;
    }



    private void printFileDetails(UsersArquives file, S3Object s3Object) {
        System.out.println("URL IMG: " + awsS3Client.getResourceUrl("deu-pet", file.getKey()));
        System.out.println("IMG KEY: " + file.getKey());
        System.out.println("Content-Type: " + s3Object.getObjectMetadata().getContentType());
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file " + e);
        }
        return convertedFile;
    }
}
