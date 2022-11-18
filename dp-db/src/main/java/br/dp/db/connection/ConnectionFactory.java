package br.dp.db.connection;

import java.sql.*;

public class ConnectionFactory {

    //    private static final String url = "jdbc:postgresql://localhost:5432/deu_pet";
    private static final String url = "jdbc:postgresql://awseb-e-jfhuhbyxtn-stack-awsebrdsdatabase-iaedpwwxuxzq.cgd35dqjznvw.us-east-1.rds.amazonaws.com:5432/deu_pet";

    private static final String username = "postgres";
    private static final String password = "postgres";

    private static Connection connection = null;

//    private static Connection getRemoteConnection() {
//        if (System.getenv("RDS_HOSTNAME") != null) {
//            try {
//                Class.forName("org.postgresql.Driver");
//                final String dbName = "deu_pet";
//                final String userName = System.getenv("RDS_USERNAME");
//                final String password = System.getenv("RDS_PASSWORD");
//                final String hostname = System.getenv("RDS_HOSTNAME");
//                final String port = System.getenv("RDS_PORT");
//                final String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
////                logger.trace("Getting remote connection with connection string from environment variables.");
//                final Connection con = DriverManager.getConnection(jdbcUrl);
////                logger.info("Remote connection successful.");
//                return con;
//            } catch (final ClassNotFoundException e) {
////                logger.warn(e.toString());
//                e.getMessage();
//            } catch (final SQLException e) {
////                logger.warn(e.toString());
//                e.getMessage();
//            }
//        }
//        return null;
//    }

    public static Connection getConnection() {

        try {

            connection = DriverManager.getConnection(url, username, password);

        } catch (final SQLException e) {

            System.out.println(e.getMessage());
        }

        return connection;
//        return getRemoteConnection();
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
