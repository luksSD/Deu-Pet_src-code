package br.dp.api.service;

import br.dp.model.Instituicao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InstituicaoService {

    List<Instituicao> readAll();

    Instituicao readById(Long id);

    Long create(Instituicao entity);

    ResponseEntity<Boolean> update(Instituicao entity);

    ResponseEntity<Boolean> delete(Long id);
}
