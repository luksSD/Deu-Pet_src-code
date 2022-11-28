package br.dp.api.service;

import br.dp.model.UsersArquives;
import br.dp.model.Usuario;

public interface UserService {

    Usuario validateUsernameAndPassword(String username, String password);

    Usuario validateLogin(String encodedData);

    boolean checkEmailExist(String email);

    Long create(Usuario user);

    boolean changePassword(Usuario user);
}
