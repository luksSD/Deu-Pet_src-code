package br.dp.db.dao;

import br.dp.model.Animal;
import br.dp.model.ArquivoAnimal;

import java.util.List;

public interface AnimalDao {

    List<Animal> readAll();

    Animal readById(Long id);

    Long create(Animal entity);

    boolean update(Animal entity);

    boolean delete(Long id);

    Long saveFileAttributes(List<ArquivoAnimal> imagesPaths);

    List<ArquivoAnimal> loadAnimalImages(Long id);
}
