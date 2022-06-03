package br.dp.api.service;

import java.util.List;

import br.dp.model.Instituicao;

public interface InstituicaoService {

	List<Instituicao> readAll();

	Instituicao readById(Long id);

	Long create(Instituicao entity);

	boolean update(Instituicao entity);

	boolean delete(Long id);

}
