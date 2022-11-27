package br.dp.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.dp.db.connection.ConnectionFactory;
import br.dp.model.Animal;
import org.springframework.stereotype.Repository;

import br.dp.db.dao.PessoaDao;
import br.dp.model.Pessoa;

@Repository
public class PessoaDaoImpl implements PessoaDao {

	@Override
	public List<Pessoa> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa readById(final Long id) {
        Pessoa pessoa = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionFactory.getConnection();

            String sql = "select * from pessoa I inner join Usuario U on U.id = I.usuario_id" +
                " where U.id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pessoa = new Pessoa();

                pessoa.setId(resultSet.getLong("id"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setEmail(resultSet.getString("email"));
                pessoa.setCelularTelefone(resultSet.getString("celular_telefone"));
                pessoa.setSituacao(resultSet.getBoolean("situacao"));
                pessoa.setDataCadastro(resultSet.getTimestamp("data"));
                pessoa.setAceite(resultSet.getBoolean("aceite"));
                pessoa.setTipo(resultSet.getString("tipo"));
                pessoa.setLogradouro(resultSet.getString("logradouro"));
                pessoa.setNumero(resultSet.getString("numero"));
                pessoa.setCep(resultSet.getString("cep"));
                pessoa.setMunicipioId(resultSet.getLong("municipio_id"));
            }

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        } finally {

            ConnectionFactory.close(resultSet, preparedStatement, connection);
        }
        return pessoa;
	}

	@Override
	public Long create(final Pessoa entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(final Pessoa entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(final Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
