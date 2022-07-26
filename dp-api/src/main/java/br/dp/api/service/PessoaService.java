package br.dp.api.service;

import br.dp.model.Pessoa;

import java.util.List;

public interface PessoaService {

	List<Pessoa> readAll();

	Pessoa readById(Long id);

	Long create(Pessoa entity);

	boolean update(Pessoa entity);

	boolean delete(Long id);

}
