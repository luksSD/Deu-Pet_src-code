package br.dp.db.dao.impl;

import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.UserDao;
import br.dp.model.UsersArquives;
import br.dp.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;

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

    @Override
    public UsersArquives loadUserImage(final long id) {
        final UsersArquives userImg = new UsersArquives();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();

            final String sql = "SELECT * FROM arquivo_usuario WHERE usuario_id = " + id;

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userImg.setId(resultSet.getLong("id"));
                userImg.setUserId(resultSet.getLong("usuario_id"));
                userImg.setPath(resultSet.getString("caminho"));
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }

        return userImg;
    }

    @Override
    public Long create(final Usuario user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = " INSERT INTO usuario (nome, senha, email, celular_telefone, situacao, data, aceite, tipo) values (?, ? , ? , ?, ?, ?, ?, ?);";

        Long id = Long.valueOf(-1);
        try {

            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getSenha());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getCelularTelefone());
            preparedStatement.setBoolean(5, user.isSituacao());
            preparedStatement.setTimestamp(6, user.getDataCadastro());
            preparedStatement.setBoolean(7, user.isAceite());
            preparedStatement.setString(8, user.getTipo());

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }

            if (id != -1) {
                connection.commit();
            } else {
                connection.abort(null);
            }

        } catch (final Exception e) {
            System.out.println(e.getMessage());

            try {
                connection.rollback();
            } catch (final SQLException e1) {
                System.out.println(e1.getMessage());
            }
        } finally {
            ConnectionFactory.close(preparedStatement, connection);

        }

        return id;
    }
}
