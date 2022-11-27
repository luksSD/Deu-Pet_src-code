package br.dp.api.service.impl;

import br.dp.api.service.AnimalService;
import br.dp.api.service.AwsS3Service;
import br.dp.db.dao.AnimalDao;
import br.dp.db.dao.PessoaDao;
import br.dp.model.Animal;
import br.dp.model.PessoaInteressaAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalDao animalDao;

    @Autowired
    private PessoaDao pessoaDao;

    @Autowired
    private AwsS3Service fileService;

    @Override
    public List<Animal> readAll() {
        // TODO Auto-generated method stub
        return animalDao.readAll();
    }

    @Override
    public Animal readById(final Long id) {
        // TODO Auto-generated method stub
        return animalDao.readById(id);
    }

    @Override
    public Long create(final Animal entity) {
        // TODO Auto-generated method stub
        return animalDao.create(entity);
    }

    @Override
    public boolean update(final Animal entity) {
        // TODO Auto-generated method stub
        return animalDao.update(entity);
    }

    @Override
    public boolean delete(final Long id) {
        boolean response = false;
        if(animalDao.delete(id)){
           response = fileService.deleteAnimalFiles(id);
        }
        return response;
    }

    @Override
    public Long adoptionRequest(PessoaInteressaAnimal adoptionRequest) {
        return animalDao.adoptionRequest(adoptionRequest);
    }

    @Override
    public List<PessoaInteressaAnimal> readAdoptionsRequests(Long id) {

        List<PessoaInteressaAnimal> requestsList = animalDao.readAdoptionsRequests(id);
        for(PessoaInteressaAnimal request : requestsList) {
            request.setAnimal(animalDao.readById(request.getAnimalId()));
            request.setPessoa(pessoaDao.readById(request.getPessoaId()));
        }

        return requestsList;
    }

    @Override
    public PessoaInteressaAnimal readRequestById(Long id) {

        PessoaInteressaAnimal requestInfo = animalDao.readRequestsById(id);
        if(requestInfo != null) {
            requestInfo.setAnimal(animalDao.readById(requestInfo.getAnimalId()));
            requestInfo.setPessoa(pessoaDao.readById(requestInfo.getPessoaId()));

            List<String> animalFiles = fileService.downloadAnimalFiles(requestInfo.getAnimal().getId());

            if(animalFiles.size() > 0) {
                requestInfo.getAnimal().setPrimaryImagePath(animalFiles.get(0));
            }
        }

        return requestInfo;
    }

    @Override
    public boolean updateRequestStatus(PessoaInteressaAnimal entity) {
        return animalDao.updateRequestStatus(entity);
    }

    @Override
    public boolean updateAnimalStatus(Animal entity) {
        return animalDao.updateAnimalStatus(entity);
    }

}
