package br.dp.api.service.impl;

import br.dp.api.service.CityService;
import br.dp.db.dao.MunicipioDao;
import br.dp.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private MunicipioDao cityDao;

    @Override
    public Long checkExist(final Municipio municipio) {

        return cityDao.checkExist(municipio);
    }

    @Override
    public Long create(final Municipio entity) {
        return null;
    }

    @Override
    public Municipio readById(final Long id) {

        return cityDao.readById(id);
    }
}