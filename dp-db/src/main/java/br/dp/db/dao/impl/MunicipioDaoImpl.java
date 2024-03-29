package br.dp.db.dao.impl;


import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.MunicipioDao;
import br.dp.model.Municipio;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class MunicipioDaoImpl implements MunicipioDao {

    @Override
    public Long checkExist(final Municipio municipio) {
        Long id = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();

            final String sql = "select id from municipio where nome = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, municipio.getNome());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getLong(1);
            } else {
                id = create(municipio);
            }

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Long create(final Municipio entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;

        final String sql = " INSERT INTO municipio(nome, uf) values (?, ?);";

        Long id = Long.valueOf(-1);
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getUf());

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

    @Override
    public Municipio readById(final Long id) {

        Municipio city = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionFactory.getConnection();

            final String sql = "select * from municipio where id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                city = new Municipio();
                city.setId(resultSet.getLong("id"));
                city.setNome(resultSet.getString("nome"));
                city.setUf(resultSet.getString("uf"));

            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }

        return city;
    }
}