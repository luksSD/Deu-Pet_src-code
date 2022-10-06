package br.dp.api.service;

import br.dp.model.Municipio;

public interface CityService {

    Long checkExist(String name);

    Long create(Municipio entity);

}
