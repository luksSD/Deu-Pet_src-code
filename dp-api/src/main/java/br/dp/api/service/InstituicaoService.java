package br.dp.api.service;

import br.dp.model.Instituicao;
import br.dp.model.UsersArquives;

import java.util.List;

public interface InstituicaoService {

    List<Instituicao> readAll();

    Instituicao readById(Long id);

    Long create(Instituicao entity);

    boolean update(Instituicao entity);

    boolean delete(Long id);

    Long saveFileAttributes(UsersArquives imagePath);
}
