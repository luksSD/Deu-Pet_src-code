package br.dp.db.dao;

import br.dp.model.UsersArquives;
import br.dp.model.Usuario;

public interface UserDao {

    Usuario validateUsernameAndPassword(String username, String password);

    UsersArquives loadUserImage(long id);

    Long create(Usuario user);
}
