package br.dp.api.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dp.api.service.CampanhaService;
import br.dp.db.dao.CampanhaDao;
import br.dp.model.Campanha;

@Service
public class CampanhaServiceImpl implements CampanhaService {
    @Autowired
    private CampanhaDao dao;

    @Override
    public List<Campanha> readAll() {
        // TODO Auto-generated method stub
        return dao.readAll();
    }

    @Override
    public Campanha readById(final Long id) {
        // TODO Auto-generated method stub
        return dao.readById(id);
    }

    @Override
    public Long create(final Campanha entity) {
        // TODO Auto-generated method stub
        return dao.create(entity);
    }

    @Override
    public boolean update(final Campanha entity) {
        // TODO Auto-generated method stub
        return dao.update(entity);
    }

    @Override
    public boolean delete(final Long id) {
        // TODO Auto-generated method stub
        return dao.delete(id);
    }
}
