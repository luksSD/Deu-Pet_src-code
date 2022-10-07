package br.dp.web.service;

import br.dp.model.Campanha;

import java.util.List;

public interface CampainService {

    List<Campanha> readAll();

    Long create(Campanha entity);

    Campanha readById(Long id);

    boolean update(Campanha entity);

    boolean deleteById(Long id);
}
