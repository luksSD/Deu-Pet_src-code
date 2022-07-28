package br.dp.db.dao;

import java.util.List;

import br.dp.model.Animal;

public interface AnimalDao {

	List<Animal> readAll();

	Animal readById(Long id);

	Long create(Animal entity);

	boolean update(Animal entity);

	boolean delete(Long id);

}
