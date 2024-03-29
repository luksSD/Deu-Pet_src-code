package br.dp.db.dao.impl;

import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.InstituicaoDao;
import br.dp.model.Instituicao;
import br.dp.model.UsersArquives;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InstituicaoDaoImpl implements InstituicaoDao {

    @Override
    public List<Instituicao> readAll() {

        final List<Instituicao> instituicoes = new ArrayList<Instituicao>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();

            final String sql = "select * from instituicao I inner join Usuario U on U.id = I.usuario_id";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                final Instituicao instituicao = new Instituicao();

                instituicao.setId(resultSet.getLong("usuario_id"));
                instituicao.setNome(resultSet.getString("nome"));
                instituicao.setSenha(resultSet.getString("senha"));
                instituicao.setEmail(resultSet.getString("email"));
                instituicao.setCelularTelefone(resultSet.getString("celular_telefone"));
                instituicao.setSituacao(resultSet.getBoolean("situacao"));
                instituicao.setDataCadastro(resultSet.getTimestamp("data"));
                instituicao.setTipo(resultSet.getString("tipo"));
                instituicao.setCpfCnpj(resultSet.getString("cpf_cnpj"));
                instituicao.setLogradouro(resultSet.getString("logradouro"));
                instituicao.setNumero(resultSet.getString("numero"));
                instituicao.setCep(resultSet.getString("cep"));
                instituicao.setMunicipioId(resultSet.getLong("municipio_id"));

                instituicoes.add(instituicao);

            }

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }

        return instituicoes;
    }

    @Override
    public Instituicao readById(final Long id) {

        Instituicao institution = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionFactory.getConnection();

            String sql = "select * from instituicao I inner join Usuario U on U.id = I.usuario_id";
            sql += " where U.id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                institution = new Instituicao();
                institution.setId(resultSet.getLong("id"));
                institution.setNome(resultSet.getString("nome"));
                institution.setSenha(resultSet.getString("senha"));
                institution.setEmail(resultSet.getString("email"));
                institution.setCelularTelefone(resultSet.getString("celular_telefone"));
                institution.setSituacao(resultSet.getBoolean("situacao"));
                institution.setDataCadastro(resultSet.getTimestamp("data"));
                institution.setTipo(resultSet.getString("tipo"));
                institution.setCpfCnpj(resultSet.getString("cpf_cnpj"));
                institution.setLogradouro(resultSet.getString("logradouro"));
                institution.setNumero(resultSet.getString("numero"));
                institution.setCep(resultSet.getString("cep"));
                institution.setMunicipioId(resultSet.getLong("municipio_id"));
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }
        return institution;
    }

    @Override
    public Long create(final Instituicao entity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;

        final String sql = " INSERT INTO usuario (nome, senha, email, celular_telefone, situacao, data, aceite, tipo) values (?, ? , ? , ?, ?, ?, ?, ?);";

        final String sql2 = "INSERT INTO instituicao (usuario_id , cpf_cnpj, logradouro, numero, cep, municipio_id) values (? , ? , ? , ?, ?, ? );";

        Long id = Long.valueOf(-1);
        Long id2 = Long.valueOf(-1);

        try {

            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getSenha());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getCelularTelefone());
            preparedStatement.setBoolean(5, true);
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, "INSTITUICAO");

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }

//			connection.commit();

            preparedStatement2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

            preparedStatement2.setLong(1, id);
            preparedStatement2.setString(2, entity.getCpfCnpj());
            preparedStatement2.setString(3, entity.getLogradouro());
            preparedStatement2.setString(4, entity.getNumero());
            preparedStatement2.setString(5, entity.getCep());
            preparedStatement2.setLong(6, entity.getMunicipioId());

            preparedStatement2.execute();
            resultSet = preparedStatement2.getGeneratedKeys();
            if (resultSet.next()) {
                id2 = resultSet.getLong(1);
            }

            if (id != -1 && id2 != -1) {
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

    @Override
    public boolean update(final Instituicao entity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        String sql = "UPDATE usuario SET";
        sql += " nome = ?,";
        sql += " email = ?,";
        sql += " celular_telefone = ?";
        sql += " where id = ?;";

        String sql2 = "UPDATE instituicao SET";
        sql2 += " cpf_cnpj = ?,";
        sql2 += " logradouro = ?,";
        sql2 += " numero = ?,";
        sql2 += " cep = ?,";
        sql2 += " municipio_id = ?";
        sql2 += " where usuario_id = ?;";

        try {

            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getCelularTelefone());
            preparedStatement.setLong(4, entity.getId());

            preparedStatement.execute();
            connection.commit();

            preparedStatement2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

            preparedStatement2.setString(1, entity.getCpfCnpj());
            preparedStatement2.setString(2, entity.getLogradouro());
            preparedStatement2.setString(3, entity.getNumero());
            preparedStatement2.setString(4, entity.getCep());
            preparedStatement2.setLong(5, entity.getMunicipioId());
            preparedStatement2.setLong(6, entity.getId());

            preparedStatement2.execute();
            connection.commit();

            return true;

        } catch (final Exception e) {

            System.out.println(e.getMessage());

            try {
                connection.rollback();
            } catch (final SQLException e1) {
                System.out.println(e1.getMessage());

            }
            return false;
        } finally {
            ConnectionFactory.close(preparedStatement, connection);
        }
    }

    @Override
    public boolean delete(final Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "START TRANSACTION;";
        sql += "DELETE FROM instituicao WHERE usuario_id = ?;";
        sql += "DELETE FROM usuario WHERE id = ?;";
        sql += "COMMIT;";

        try {

            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, id);

            preparedStatement.execute();

            connection.commit();

            return true;

        } catch (final Exception e) {

            try {
                connection.rollback();
            } catch (final SQLException e1) {
                System.out.println(e1.getMessage());

            }
            return false;
        } finally {
            ConnectionFactory.close(preparedStatement, connection);
        }
    }

    @Override
    public Long saveFileAttributes(final UsersArquives imagePath) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO arquivo_usuario ";
        sql += " (usuario_id, caminho)";
        sql += "VALUES(?, ?);";

        Long id = Long.valueOf(1);

        try {

            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, imagePath.getUserId());
            preparedStatement.setString(2, imagePath.getPath());

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                id = Long.valueOf(-1);
            }


            if (id != -1) {
                connection.commit();
            } else {
                connection.abort(null);
            }

        } catch (final Exception e) {
            try {
                connection.rollback();
            } catch (final SQLException e1) {
                System.out.println(e1.getMessage());
            } finally {
                ConnectionFactory.close(resultSet, preparedStatement, connection);
            }
        }

        return id;
    }

}
