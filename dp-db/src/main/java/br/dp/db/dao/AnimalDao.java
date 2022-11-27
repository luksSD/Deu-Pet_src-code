package br.dp.db.dao;

import br.dp.model.Animal;
import br.dp.model.AnimalsArquives;
import br.dp.model.PessoaInteressaAnimal;

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

    Long adoptionRequest(PessoaInteressaAnimal adoptionRequest);

    List<PessoaInteressaAnimal> readAdoptionsRequests(Long id);

    PessoaInteressaAnimal readRequestsById(Long id);

    boolean updateRequestStatus(PessoaInteressaAnimal entity);

    boolean updateAnimalStatus(Animal entity);
}
