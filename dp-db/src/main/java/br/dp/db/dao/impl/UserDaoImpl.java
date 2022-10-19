package br.dp.db.dao.impl;

import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.UserDao;
import br.dp.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public Usuario validateUsernameAndPassword(final String username, final String password) {


        Usuario user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionFactory.getConnection();

            final String sql = "select * from usuario WHERE email = ? AND senha = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new Usuario();

                user.setId(resultSet.getLong("id"));
                user.setNome(resultSet.getString("nome"));
                user.setSenha(resultSet.getString("senha"));
                user.setEmail(resultSet.getString("email"));
                user.setCelularTelefone(resultSet.getString("celular_telefone"));
                user.setSituacao(resultSet.getBoolean("situacao"));
                user.setDataCadastro(resultSet.getTimestamp("data"));
                user.setTipo(resultSet.getString("tipo"));

            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());

        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }
        return user;
    }
}
