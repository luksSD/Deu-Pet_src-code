package br.dp.api.service;

import java.util.List;

import br.dp.model.Animal;

public interface AnimalService {

	List<Animal> readAll();

	Animal readById(Long id);

	Long create(Animal entity);

	boolean update(Animal entity);

	boolean delete(Long id);
}
