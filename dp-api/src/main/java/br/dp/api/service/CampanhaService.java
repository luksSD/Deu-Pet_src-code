package br.dp.api.service;

import br.dp.model.CampainsArquives;
import br.dp.model.Campanha;

import java.util.List;

public interface CampanhaService {

    List<Campanha> readAll();

    Campanha readById(Long id);

    Long create(Campanha entity);

    boolean update(Campanha entity);

    boolean delete(Long id);

}
