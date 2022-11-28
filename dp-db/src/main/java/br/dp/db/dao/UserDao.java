package br.dp.db.dao;

import br.dp.model.UsersArquives;
import br.dp.model.Usuario;

public interface UserDao {

    Usuario validateUsernameAndPassword(String username, String password);

    Usuario checkEmailExist(String email);

    UsersArquives loadUserImg(long id);

    Long saveFileAttributes(UsersArquives imagePath);

    Long create(Usuario user);

    boolean updateFileAttributes(UsersArquives file);

    boolean deleteFile(long id);

    boolean changePassword(Usuario user);
}
