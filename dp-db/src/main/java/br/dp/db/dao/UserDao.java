package br.dp.db.dao;

import br.dp.model.UsersArquives;
import br.dp.model.Usuario;

public interface UserDao {

    Usuario validateUsernameAndPassword(String username, String password);

    UsersArquives loadImage(long id);

    Long saveFileAttributes(UsersArquives imagePath);

    Long create(Usuario user);
}
