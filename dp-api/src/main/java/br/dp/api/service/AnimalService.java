package br.dp.api.service;

import br.dp.model.Animal;
import br.dp.model.ArquivoAnimal;

import java.util.List;

public interface AnimalService {

    List<Animal> readAll();

    Animal readById(Long id);

    Long create(Animal entity);

    boolean update(Animal entity);

    boolean delete(Long id);

    Long saveFileAttributes(List<ArquivoAnimal> imagesAttributes);

    List<ArquivoAnimal> loadAnimalImgs(Long id);
}
