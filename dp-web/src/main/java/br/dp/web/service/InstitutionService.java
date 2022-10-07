package br.dp.web.service;

import br.dp.model.Instituicao;

import java.util.List;

public interface InstitutionService {

    List<Instituicao> readAll();

    Instituicao readById(Long id);

    boolean update(Instituicao entity);

    boolean deleteById(Long id);

    Long create(Instituicao entity);

}
