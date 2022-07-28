package br.dp.db.dao;

import java.util.List;

import br.dp.model.Campanha;

public interface CampanhaDao {

	List<Campanha> readAll();

	Campanha readById(Long id);

	Long create(Campanha entity);

	boolean update(Campanha entity);

	boolean delete(Long id);

}
