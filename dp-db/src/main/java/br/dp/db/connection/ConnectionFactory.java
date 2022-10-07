package br.dp.db.connection;

import java.sql.*;

public class ConnectionFactory {

    private static final String url = "jdbc:postgresql://localhost:5432/deupet";

    private static final String username = "postgres";
    private static final String password = "rootpass4intelbras";

    private static Connection connection = null;

    public static Connection getConnection() {

        try {

            connection = DriverManager.getConnection(url, username, password);

        } catch (final SQLException e) {

            System.out.println(e.getMessage());
        }

        return connection;
    }

    private static void closeConnection() {

        try {

            connection.close();

        } catch (final SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    private static void closeResultSet(final ResultSet resultSet) {

        try {
            resultSet.close();
        } catch (final SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void closePreparedStatement(final PreparedStatement preparedStatement) {

        try {
            preparedStatement.close();
        } catch (final SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void close(final ResultSet resultSet, final PreparedStatement preparedStatement,
                             final Connection connection) {

        closeConnection();
        closePreparedStatement(preparedStatement);
        closeResultSet(resultSet);

    }

    public static void close(final PreparedStatement preparedStatement, final Connection connection) {

        closeConnection();
        closePreparedStatement(preparedStatement);

    }

}
