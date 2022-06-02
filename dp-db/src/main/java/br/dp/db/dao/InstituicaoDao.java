package br.dp.db.dao;

import java.util.List;

import br.dp.model.Instituicao;

public interface InstituicaoDao {

	List<Instituicao> readAll();

	Instituicao readById(Long id);

	Long create(Instituicao entity);

	boolean update(Instituicao entity);

	boolean delete(Long id);

}
