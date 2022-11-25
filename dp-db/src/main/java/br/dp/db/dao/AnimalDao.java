package br.dp.db.dao;

import br.dp.model.Animal;
import br.dp.model.AnimalsArquives;
import br.dp.model.CampainsArquives;

import java.util.List;

public interface AnimalDao {

    List<Animal> readAll();

    Animal readById(Long id);

    Long create(Animal entity);

    boolean update(Animal entity);

    boolean delete(Long id);

    boolean saveFileAttributes(List<AnimalsArquives> imagesPaths);

    List<AnimalsArquives> loadImages(Long id);

    boolean deleteFiles(long id);

    boolean updateFilesAttributes(List<AnimalsArquives> file);
}
