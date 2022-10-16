package br.dp.web.service;

import br.dp.model.Instituicao;
import br.dp.model.UsersArquives;

import java.util.List;

public interface InstitutionService {

    List<Instituicao> readAll();

    Instituicao readById(Long id);

    boolean update(Instituicao entity);

    boolean deleteById(Long id);

    Long create(Instituicao entity);

    Long saveFileAttributes(UsersArquives userImage);
}
