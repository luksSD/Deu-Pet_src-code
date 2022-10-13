package br.dp.web.service;

import br.dp.model.Animal;
import br.dp.model.ArquivoAnimal;

import java.util.List;

public interface AnimalService {

    List<Animal> readAll();

    Animal readById(Long id);

    boolean update(Animal entity);

    boolean delete(Long id);

    Long create(Animal entity);

    Long saveFileAttributes(List<ArquivoAnimal> entity);

    List<ArquivoAnimal> loadAnimalImgs(Long id);

}
