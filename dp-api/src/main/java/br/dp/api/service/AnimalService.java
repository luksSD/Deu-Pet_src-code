package br.dp.api.service;

import br.dp.model.Animal;
import br.dp.model.PessoaInteressaAnimal;

import java.util.List;

public interface AnimalService {

    List<Animal> readAll();

    Animal readById(Long id);

    Long create(Animal entity);

    boolean update(Animal entity);

    boolean delete(Long id);

    Long adoptionRequest(PessoaInteressaAnimal adoptionRequest);

    List<PessoaInteressaAnimal> readAdoptionsRequests(Long id);

    PessoaInteressaAnimal readRequestById(Long id);

    boolean updateRequestStatus(PessoaInteressaAnimal entity);

    boolean updateAnimalStatus(Animal entity);
}
