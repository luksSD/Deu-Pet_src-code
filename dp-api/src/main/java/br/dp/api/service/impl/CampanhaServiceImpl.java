package br.dp.api.service.impl;

import br.dp.api.service.CampanhaService;
import br.dp.db.dao.CampanhaDao;
import br.dp.model.CampainsArquives;
import br.dp.model.Campanha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Long saveFileAttributes(final CampainsArquives imagePath) {
        return dao.saveFileAttributes(imagePath);
    }

    @Override
    public CampainsArquives LoadCampainImg(final long id) {
        return dao.loadImage(id);
    }
}
