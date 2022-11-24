package br.dp.db.dao.impl;

import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.CampanhaDao;
import br.dp.model.CampainsArquives;
import br.dp.model.Campanha;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
                campain.setDataInicio(resultSet.getDate("data_inicio"));
                campain.setDataFim(resultSet.getDate("data_fim"));
                campain.setId(resultSet.getLong("id"));

                campanha.add(campain);
            }


        } catch (final Exception e) {
            System.out.println(e.getMessage());
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
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                campanha = new Campanha();

                campanha.setId(resultSet.getLong("id"));
                campanha.setTitulo(resultSet.getString("titulo"));
                campanha.setDataInicio(resultSet.getDate("data_inicio"));
                campanha.setDataFim(resultSet.getDate("data_fim"));
                campanha.setDescricao(resultSet.getString("descricao"));
                campanha.setRequisitos(resultSet.getString("requisitos"));
                campanha.setUrlForm(resultSet.getString("formulario_url"));
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
        sql += "(titulo, descricao, requisitos, data_inicio, data_fim, instituicao_id, formulario_url ) ";
        sql += "values(?,?,?,?,?,?,?)";

        Long id = Long.valueOf(-1);

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getTitulo());
            preparedStatement.setString(2, entity.getDescricao());
            preparedStatement.setString(3, entity.getRequisitos());
            preparedStatement.setDate(4, entity.getDataInicio());
            preparedStatement.setDate(5, entity.getDataFim());
            preparedStatement.setLong(6, entity.getInstituicaoId());
            preparedStatement.setString(7, entity.getUrlForm());


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

        String sql = "update campanha set titulo = ?, ";
        sql += "descricao = ?, ";
        sql += "requisitos = ?, ";
        sql += "data_inicio = ?, ";
        sql += "data_fim = ?, ";
        sql += "instituicao_id = ?";
        if (entity.getUrlForm() != null && !entity.getUrlForm().isEmpty()) {
            sql += ", formulario_url = ?";
        }
        sql += " where id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getTitulo());
            preparedStatement.setString(2, entity.getDescricao());
            preparedStatement.setString(3, entity.getRequisitos());
            preparedStatement.setDate(4, entity.getDataInicio());
            preparedStatement.setDate(5, entity.getDataFim());
            preparedStatement.setLong(6, entity.getInstituicaoId());
            if (entity.getUrlForm() != null && !entity.getUrlForm().isEmpty()) {
                preparedStatement.setString(7, entity.getUrlForm());
                preparedStatement.setLong(8, entity.getId());
            } else {
                preparedStatement.setLong(7, entity.getId());
            }

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

    @Override
    public Long saveFileAttributes(final CampainsArquives imagePath) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO arquivo_campanha ";
        sql += " (campanha_id, caminho, tipo, chave)";
        sql += "VALUES(?, ?, ?, ?);";

        Long id = Long.valueOf(1);

        try {

            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, imagePath.getCampainId());
            preparedStatement.setString(2, imagePath.getPath());
            preparedStatement.setString(3, imagePath.getType());
            preparedStatement.setString(4, imagePath.getKey());

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
    public CampainsArquives loadImage(final long id) {
        final CampainsArquives campainImg = new CampainsArquives();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();

            final String sql = "SELECT * FROM arquivo_campanha WHERE campanha_id = " + id;

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                campainImg.setId(resultSet.getLong("id"));
                campainImg.setCampainId(resultSet.getLong("campanha_id"));
                campainImg.setPath(resultSet.getString("caminho"));
                campainImg.setType(resultSet.getString("tipo"));
                campainImg.setKey(resultSet.getString("chave"));
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }

        return campainImg;
    }

    @Override
    public boolean deleteFile(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String sql = "delete from arquivo_campanha where campanha_id = ?";

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

    @Override
    public boolean updateFileAttributes(CampainsArquives file) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "update arquivo_campanha set " +
            "caminho = ?, " +
            "tipo = ?, " +
            "chave = ? " +
            "where campanha_id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, file.getPath());
            preparedStatement.setString(2, file.getType());
            preparedStatement.setString(3, file.getKey());
            preparedStatement.setLong(4, file.getCampainId());

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


