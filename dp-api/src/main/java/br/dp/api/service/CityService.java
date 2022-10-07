package br.dp.api.service;

import br.dp.model.Municipio;

public interface CityService {

    Long checkExist(Municipio entity);

    Long create(Municipio entity);

    Municipio readById(Long id);

}