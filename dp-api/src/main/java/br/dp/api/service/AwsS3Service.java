package br.dp.api.service;

import br.dp.model.CampainsArquives;
import br.dp.model.UsersArquives;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwsS3Service {

    String downloadUserFile(Long id);
    List<String> downloadAnimalFiles(Long id);
    String downloadCampainFile(Long id);
    Boolean uploadUserFile(UsersArquives userFile);
    Boolean uploadCampainFile(CampainsArquives file);

    Boolean uploadFiles(Long id);
}
