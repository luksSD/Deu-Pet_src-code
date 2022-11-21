package br.dp.db.dao.impl;

import br.dp.db.connection.ConnectionFactory;
import br.dp.db.dao.AnimalDao;
import br.dp.model.Animal;
import br.dp.model.AnimalsArquives;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                animal.setNome(resultSet.getString("nome"));
                animal.setTipo(resultSet.getString("tipo"));
                animal.setSexo(resultSet.getString("sexo"));
                animal.setPeso(resultSet.getDouble("peso"));
                animal.setPorte(resultSet.getString("porte"));
                animal.setRaca(resultSet.getString("raca"));
                animal.setSituacao(resultSet.getString("situacao"));
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
                animal.setNome(resultSet.getString("nome"));
                animal.setSexo(resultSet.getString("sexo"));
                animal.setPeso(resultSet.getDouble("peso"));
                animal.setPorte(resultSet.getString("porte"));
                animal.setRaca(resultSet.getString("raca"));
                animal.setSituacao(resultSet.getString("situacao"));
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
        sql += " (nome, tipo, sexo, peso, porte, ";
        sql += " raca, situacao, temperamento, ";
        sql += " pelagem_primaria, pelagem_secundaria, instituicao_id) ";
        sql += "VALUES(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Long id = Long.valueOf(-1);

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getTipo());
            preparedStatement.setString(3, entity.getSexo());
            preparedStatement.setDouble(4, entity.getPeso());
            preparedStatement.setString(5, entity.getPorte());
            preparedStatement.setString(6, entity.getRaca());
            preparedStatement.setString(7, entity.getSituacao());
            preparedStatement.setString(8, entity.getTemperamento());
            preparedStatement.setString(9, entity.getPelagemPrimaria());
            preparedStatement.setString(10, entity.getPelagemSecundaria());
            preparedStatement.setLong(11, 4);

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

        String sql = "UPDATE animal Set nome = ?, ";
        sql += " tipo = ?, ";
        sql += " sexo = ?, ";
        sql += " peso = ?, ";
        sql += " porte = ?, ";
        sql += " raca = ?, ";
        sql += " situacao = ?, ";
        sql += " temperamento = ?, ";
        sql += " pelagem_primaria = ?, ";
        sql += " pelagem_secundaria = ?,  ";
        sql += " instituicao_id = ?  ";
        sql += " WHERE id = ?;  ";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, "Cachorro");
            preparedStatement.setString(3, "Macho");
            preparedStatement.setDouble(4, entity.getPeso());
            preparedStatement.setString(5, entity.getPorte());
            preparedStatement.setString(6, entity.getRaca());
            preparedStatement.setString(7, entity.getSituacao());
            preparedStatement.setString(8, entity.getTemperamento());
            preparedStatement.setString(9, entity.getPelagemPrimaria());
            preparedStatement.setString(10, entity.getPelagemSecundaria());
            preparedStatement.setLong(11, 4);
            preparedStatement.setLong(12, entity.getId());

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

    @Override
    public boolean saveFileAttributes(final List<AnimalsArquives> animalFiles) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO arquivo_animal ";
        sql += " (animal_id, caminho, primaria)";
        sql += "VALUES(?, ?, ?);";

        boolean response = true;

        try {

            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            for (final AnimalsArquives imgAtt : animalFiles) {
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setLong(1, imgAtt.getAnimalID());
                preparedStatement.setString(2, imgAtt.getPath());
                preparedStatement.setBoolean(3, imgAtt.isPrimary());
                preparedStatement.setString(4, imgAtt.getType());
                preparedStatement.setString(5, imgAtt.getKey());

                preparedStatement.execute();

                resultSet = preparedStatement.getGeneratedKeys();

                if (!resultSet.next()) {
                    response = false;
                    break;
                }
            }

            if (response) {
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

        return response;
    }

    @Override
    public List<AnimalsArquives> loadImages(final Long id) {

        final List<AnimalsArquives> animalsImagesList = new ArrayList<AnimalsArquives>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();

            final String sql = "SELECT * FROM arquivo_animal WHERE animal_id = " + id + " ORDER BY id ASC";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                final AnimalsArquives animalImage = new AnimalsArquives();

                animalImage.setId(resultSet.getLong("id"));
                animalImage.setAnimalID(resultSet.getLong("animal_id"));
                animalImage.setPath(resultSet.getString("caminho"));
                animalImage.setPrimary(resultSet.getBoolean("primaria"));
                animalImage.setType(resultSet.getString("tipo"));
                animalImage.setKey(resultSet.getString("chave"));

                animalsImagesList.add(animalImage);
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }

        return animalsImagesList;
    }

}
