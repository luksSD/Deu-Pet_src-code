package br.dp.db.dao;

import br.dp.model.Usuario;

public interface UserDao {

    Usuario validateUsernameAndPassword(String username, String password);
}
