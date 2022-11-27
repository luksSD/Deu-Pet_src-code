package br.dp.web.service;

import br.dp.model.Animal;
import br.dp.model.AnimalsArquives;
import br.dp.model.PessoaInteressaAnimal;

import java.util.List;

public interface AnimalService {

    List<Animal> readAll();

    Animal readById(Long id);

    boolean update(Animal entity);

    boolean delete(Long id);

    Long create(Animal entity);

    List<PessoaInteressaAnimal> readAdoptionsRequests(Long id);

    PessoaInteressaAnimal readRequestById(Long id);

    boolean requestStatusApproved(Long id);

    boolean requestStatusDenied(Long id);
}
