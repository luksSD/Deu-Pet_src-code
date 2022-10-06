package br.dp.db.dao;

import br.dp.model.Municipio;

public interface MunicipioDao {

    Long checkExist(Municipio municipio);

    Long create(Municipio entity);

}
