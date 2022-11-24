package br.dp.api.service;

import br.dp.model.AnimalsArquives;
import br.dp.model.CampainsArquives;
import br.dp.model.UsersArquives;

import java.util.List;

public interface AwsS3Service {

    String downloadUserFile(Long id);
    List<String> downloadAnimalFiles(Long id);
    String downloadCampainFile(Long id);
    Boolean uploadUserFile(UsersArquives userFile);
    Boolean uploadCampainFile(CampainsArquives file);
    Boolean uploadAnimalFiles(List<AnimalsArquives> animalFiles);
    Boolean deleteUserFile(long id);
    Boolean deleteCampaignFile(long id);
}
