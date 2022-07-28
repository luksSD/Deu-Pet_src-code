package br.dp.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dp.api.service.AnimalService;
import br.dp.db.dao.AnimalDao;
import br.dp.model.Animal;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	private AnimalDao dao;

	@Override
	public List<Animal> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

	@Override
	public Animal readById(final Long id) {
		// TODO Auto-generated method stub
		return dao.readById(id);
	}

	@Override
	public Long create(final Animal entity) {
		// TODO Auto-generated method stub
		return dao.create(entity);
	}

	@Override
	public boolean update(final Animal entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	@Override
	public boolean delete(final Long id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

}
