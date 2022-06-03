package br.dp.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.AnimalDao;
import br.dp.model.Animal;

@Repository
public class AnimalDaoImpl implements AnimalDao {

	@Override
	public List<Animal> readAll() {

		final List<Animal> animais = new ArrayList<Animal>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM animal";

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Animal animal = new Animal();

				animal.setId(resultSet.getLong("id"));
				animal.setTipo(resultSet.getString("tipo"));
				animal.setSexo(resultSet.getString("sexo"));
				animal.setPeso(resultSet.getDouble("peso"));
				animal.setPorte(resultSet.getString("porte"));
				animal.setRaca(resultSet.getString("raca"));
				animal.setSituacaoAodocao(resultSet.getBoolean("situacao_adocao"));
				animal.setTemperamento(resultSet.getString("temperamento"));
				animal.setPelagemPrimaria(resultSet.getString("pelagem_primaria"));
				animal.setPelagemSecundaria(resultSet.getString("pelagem_secundaria"));

				animais.add(animal);

			}

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return animais;
	}

	@Override
	public Animal readById(final Long id) {

		Animal animal = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM animal WHERE id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				animal = new Animal();

				animal.setId(resultSet.getLong("id"));
				animal.setTipo(resultSet.getString("tipo"));
				animal.setSexo(resultSet.getString("sexo"));
				animal.setPeso(resultSet.getDouble("peso"));
				animal.setPorte(resultSet.getString("porte"));
				animal.setRaca(resultSet.getString("raca"));
				animal.setSituacaoAodocao(resultSet.getBoolean("situacao_adocao"));
				animal.setTemperamento(resultSet.getString("temperamento"));
				animal.setPelagemPrimaria(resultSet.getString("pelagem_primaria"));
				animal.setPelagemSecundaria(resultSet.getString("pelagem_secundaria"));

			}

		} catch (final Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return animal;
	}

	@Override
	public Long create(final Animal entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO animal";
		sql += " (tipo, sexo, peso, porte, ";
		sql += " raca, situacao_adocao, temperamento, ";
		sql += " pelagem_primaria, pelagem_secundaria) ";
		sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Long id = Long.valueOf(-1);

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getTipo());
			preparedStatement.setString(2, entity.getSexo());
			preparedStatement.setDouble(3, entity.getPeso());
			preparedStatement.setString(4, entity.getPorte());
			preparedStatement.setString(5, entity.getRaca());
			preparedStatement.setBoolean(6, false);
			preparedStatement.setString(7, entity.getTemperamento());
			preparedStatement.setString(8, entity.getPelagemPrimaria());
			preparedStatement.setString(9, entity.getPelagemSecunaria());

			preparedStatement.execute();

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next())
				;
			{
				id = resultSet.getLong(1);
			}

			connection.commit();

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

	@Override
	public boolean update(final Animal entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE animal Set ";
		sql += " tipo = ?, ";
		sql += " sexo = ?  ";
		sql += " peso = ?  ";
		sql += " porte = ?  ";
		sql += " raca = ?  ";
		sql += " situacao_adocao = ?  ";
		sql += " temperamento = ?  ";
		sql += " pelagem_primaria = ?  ";
		sql += " pelagem_secundaria = ?  ";
		sql += " WHERE id = ?;  ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, entity.getTipo());
			preparedStatement.setString(2, entity.getSexo());
			preparedStatement.setDouble(3, entity.getPeso());
			preparedStatement.setString(4, entity.getPorte());
			preparedStatement.setString(5, entity.getRaca());
			preparedStatement.setBoolean(6, entity.isSituacaoAodocao());
			preparedStatement.setString(7, entity.getTemperamento());
			preparedStatement.setString(8, entity.getPelagemPrimaria());
			preparedStatement.setString(9, entity.getPelagemSecunaria());

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
	public boolean delete(final Long id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String sql = "DELETE FROM animal WHERE id = ?; ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);

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
