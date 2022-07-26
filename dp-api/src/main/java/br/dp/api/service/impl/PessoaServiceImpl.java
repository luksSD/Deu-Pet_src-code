package br.dp.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dp.api.service.PessoaService;
import br.dp.db.dao.PessoaDao;
import br.dp.model.Pessoa;


@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaDao dao;

    @Override
    public List<Pessoa> readAll() {
        // TODO Auto-generated method stub
        return dao.readAll();
    }

    @Override
    public Pessoa readById(final Long id) {
        // TODO Auto-generated method stub
        return dao.readById(id);
    }

    @Override
    public Long create(final Pessoa entity) {
        // TODO Auto-generated method stub
        return dao.create(entity);
    }

    @Override
    public boolean update(final Pessoa entity) {
        // TODO Auto-generated method stub
        return dao.update(entity);
    }

    @Override
    public boolean delete(final Long id) {
        // TODO Auto-generated method stub
        return dao.delete(id);
    }

}
