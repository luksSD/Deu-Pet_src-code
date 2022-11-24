package br.dp.api.service.impl;

import br.dp.api.service.AwsS3Service;
import br.dp.api.service.InstituicaoService;
import br.dp.db.dao.InstituicaoDao;
import br.dp.db.dao.MunicipioDao;
import br.dp.model.Instituicao;
import br.dp.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoServiceImpl implements InstituicaoService {

    @Autowired
    private InstituicaoDao instituicaoDao;

    @Autowired
    private MunicipioDao municipioDao;

    @Autowired
    private AwsS3Service fileService;

    @Override
    public List<Instituicao> readAll() {
        // TODO Auto-generated method stub
        return instituicaoDao.readAll();
    }

    @Override
    public Instituicao readById(final Long id) {
        // TODO Auto-generated method stub
        return instituicaoDao.readById(id);
    }

    @Override
    public Long create(final Instituicao entity) {

        final Long id;
        final Municipio municipio = new Municipio();

        municipio.setNome(entity.getMuinicipioNome());
        municipio.setUf(entity.getUf());

        id = municipioDao.checkExist(municipio);

        if (id != -1) {
            entity.setMunicipioId(id);
        } else {
            return id;
        }

        return instituicaoDao.create(entity);
    }

    @Override
    public Boolean update(final Instituicao entity) {

        final Long id;
        final Municipio municipio = new Municipio();

        municipio.setNome(entity.getMuinicipioNome());
        municipio.setUf(entity.getUf());

        id = municipioDao.checkExist(municipio);

        if (id != -1) {
            entity.setMunicipioId(id);
        } else {
            return false;
        }

        return instituicaoDao.update(entity);
    }

    @Override
    public Boolean delete(final Long id) {

        if(instituicaoDao.delete(id)){
            return fileService.deleteUserFile(id);
        }

        return false;
    }

}
