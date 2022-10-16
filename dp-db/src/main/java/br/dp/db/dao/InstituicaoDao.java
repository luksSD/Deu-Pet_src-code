package br.dp.db.dao;

import br.dp.model.Instituicao;
import br.dp.model.UsersArquives;

import java.util.List;

public interface InstituicaoDao {

    List<Instituicao> readAll();

    Instituicao readById(Long id);

    Long create(Instituicao entity);

    boolean update(Instituicao entity);

    boolean delete(Long id);

    Long saveFileAttributes(UsersArquives imagePath);

    UsersArquives loadUserImage(long id);
}
