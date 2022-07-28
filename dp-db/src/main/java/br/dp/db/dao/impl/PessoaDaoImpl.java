package br.dp.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.dp.db.dao.PessoaDao;
import br.dp.model.Pessoa;

@Repository
public class PessoaDaoImpl implements PessoaDao {

	@Override
	public List<Pessoa> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa readById(final Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(final Pessoa entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(final Pessoa entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(final Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
