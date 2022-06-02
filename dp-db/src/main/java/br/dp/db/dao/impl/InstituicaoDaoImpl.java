package br.dp.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.InstituicaoDao;
import br.dp.model.Instituicao;

public class InstituicaoDaoImpl implements InstituicaoDao {

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

				instituicao.setId(resultSet.getLong("id"));
				instituicao.setNome(resultSet.getString("nome"));
				instituicao.setSenha(resultSet.getString("senha"));
				instituicao.setEmail(resultSet.getString("email"));
				instituicao.setCelular(resultSet.getString("celular"));
				instituicao.setSituacao(resultSet.getBoolean("situacao"));
//				instituicao.setDataCadastro(resultSet.getTimestamp("data"));
				instituicao.setTipo(resultSet.getString("tipo"));
				instituicao.setTelefone(resultSet.getString("telefone"));
				instituicao.setCnpj(resultSet.getString("cnpj"));
				instituicao.setLogradouro(resultSet.getString("logradouro"));
				instituicao.setNumero(resultSet.getString("numero"));
				instituicao.setCep(resultSet.getString("cep"));
				instituicao.setCpf(resultSet.getString("cpf"));
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

	public Instituicao readById(final Long id) {

		Instituicao instituicao = null;

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

				instituicao = new Instituicao();

				instituicao.setId(resultSet.getLong("id"));
				instituicao.setNome(resultSet.getString("nome"));
				instituicao.setSenha(resultSet.getString("senha"));
				instituicao.setEmail(resultSet.getString("email"));
				instituicao.setCelular(resultSet.getString("celular"));
				instituicao.setSituacao(resultSet.getBoolean("situacao"));
//				instituicao.setDataCadastro(resultSet.getTimestamp("data"));
				instituicao.setTipo(resultSet.getString("tipo"));
				instituicao.setTelefone(resultSet.getString("telefone"));
				instituicao.setCnpj(resultSet.getString("cnpj"));
				instituicao.setLogradouro(resultSet.getString("logradouro"));
				instituicao.setNumero(resultSet.getString("numero"));
				instituicao.setCep(resultSet.getString("cep"));
				instituicao.setCpf(resultSet.getString("cpf"));
				instituicao.setMunicipioId(resultSet.getLong("municipio_id"));

			}

		} catch (final Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return instituicao;
	}

	public Long create(final Instituicao entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet = null;

		final String sql = " INSERT INTO usuario (nome, senha, email, celular, situacao, data, tipo) values (? , ? , ?, ?, ?, ?, ?);";

		final String sql2 = "INSERT INTO instituicao (usuario_id , telefone, cnpj, logradouro, numero, cep, cpf, municipio_id) values (? , ? , ? , ? , ? , ?, ?, ? );";

		Long id = Long.valueOf(-1);

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getSenha());
			preparedStatement.setString(3, entity.getEmail());
			preparedStatement.setString(4, entity.getCelular());
			preparedStatement.setBoolean(5, true);
			preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(7, entity.getTipo());

			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}

			connection.commit();

			preparedStatement2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

			preparedStatement2.setLong(1, id);
			preparedStatement2.setString(2, entity.getTelefone());
			preparedStatement2.setString(3, entity.getCnpj());
			preparedStatement2.setString(4, entity.getLogradouro());
			preparedStatement2.setString(5, entity.getNumero());
			preparedStatement2.setString(6, entity.getCep());
			preparedStatement2.setString(7, entity.getCpf());
			preparedStatement2.setLong(8, entity.getMunicipioId());

			resultSet = preparedStatement2.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			} else {
				id = Long.valueOf(-1);
			}

			connection.commit();

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

	public boolean update(final Instituicao entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;

		String sql = "UPDATE usuario SET";
		sql += " nome = ?,";
		sql += " email = ?";
		sql += " celular = ?";
		sql += " where id = ?;";

		String sql2 = "UPDATE instituicao SET";
		sql2 += " telefone = ?,";
		sql2 += " cnpj = ?,";
		sql2 += " logradouro = ?,";
		sql2 += " numero = ?";
		sql2 += " cep = ?";
		sql2 += " cpf = ?";
		sql2 += " municipio_id = ?";
		sql2 += " where usuario_id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getEmail());
			preparedStatement.setString(3, entity.getCelular());

			preparedStatement.execute();
			connection.commit();

			preparedStatement2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

			preparedStatement2.setString(1, entity.getTelefone());
			preparedStatement2.setString(2, entity.getCnpj());
			preparedStatement2.setString(3, entity.getLogradouro());
			preparedStatement2.setString(4, entity.getNumero());
			preparedStatement2.setString(5, entity.getCep());
			preparedStatement2.setString(6, entity.getCpf());
			preparedStatement2.setLong(7, entity.getMunicipioId());

			preparedStatement2.execute();
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

}
