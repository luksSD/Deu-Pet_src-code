package br.dp.db.dao.impl;

import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.CampanhaDao;
import br.dp.model.Campanha;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CampanhaDaoImpl implements CampanhaDao {
    @Override
    public List<Campanha> readAll() {

        final List<Campanha> campanha = new ArrayList<Campanha>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            final String sql = "select * from campanha";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                final Campanha campain = new Campanha();
                campain.setTitulo(resultSet.getString("titulo"));
                campain.setDataInicio(resultSet.getTimestamp("data_inicio"));
                campain.setDataFim(resultSet.getTimestamp("data_fim"));
                campain.setId(resultSet.getLong("id"));

                campanha.add(campain);
            }


        } catch (final Exception e) {

        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }

        return campanha;
    }

    @Override
    public Campanha readById(final Long id) {
        Campanha campanha = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();

            final String sql = "select * from campanha where id = ?";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                campanha = new Campanha();

                campanha.setId(resultSet.getLong("id"));
                campanha.setTitulo(resultSet.getString("titulo"));
                campanha.setDataInicio(resultSet.getTimestamp("data_inicio"));
                campanha.setDataFim(resultSet.getTimestamp("data_fim"));
            }

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }
        return campanha;
    }

    @Override
    public Long create(final Campanha entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //nesse ponto tem que verificar se vai colocar o id fixo da instituição pra hoje ou pegar de outra entity
        String sql = "insert into campanha";
        sql += "(descricao, requisitos, data_inicio, data_fim, instituicao_id, formulario_id ) ";
        sql += "values(?,?,?,?,?,?)";

        Long id = Long.valueOf(-1);


        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getDescricao());
            preparedStatement.setString(2, entity.getRequisitos());
            preparedStatement.setTimestamp(3, entity.getDataInicio());
            preparedStatement.setTimestamp(4, entity.getDataFim());
            preparedStatement.setLong(5, 4);
            preparedStatement.setLong(6, 4);

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }

            connection.commit();

        } catch (final Exception e) {
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
    public boolean update(final Campanha entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "update campanha set titulo = ?,";
        sql += "descricao = ?,";
        sql += "requisitos = ?,";
        sql += "data_inicio = ?,";
        sql += "data_fim = ?";
        sql += "where id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getDescricao());
            preparedStatement.setString(2, entity.getRequisitos());
            preparedStatement.setTimestamp(3, entity.getDataInicio());
            preparedStatement.setTimestamp(4, entity.getDataFim());
            preparedStatement.setLong(5, entity.getId());

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
        final String sql = "delete from campanha where id = ?";

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
