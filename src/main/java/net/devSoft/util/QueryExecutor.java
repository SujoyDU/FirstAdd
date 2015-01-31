package net.devSoft.util;

import java.sql.*;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sujoy on 1/31/2015.
 */
public class QueryExecutor {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = ConnectionManager.getConnection();
        preparedStatement = connection.prepareStatement(query);

        mapParamsIntoPreparedStatement(preparedStatement, params);

        preparedStatement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeConnection(connection, null, preparedStatement);
    }
}

    public static <E> List<E> executeSelectQuery(String query, ResultSetProcessor<E> resultSetProcessor, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<E> results = new ArrayList<>();

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(query);

            mapParamsIntoPreparedStatement(preparedStatement, params);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                results.add(resultSetProcessor.getObjectFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, resultSet, preparedStatement);
        }
        return results;
    }

    private static void mapParamsIntoPreparedStatement(PreparedStatement preparedStatement, Object... params) throws SQLException {
        int itemCount = 1;

        for (Object parameter : params) {
            if (parameter instanceof String) {
                preparedStatement.setString(itemCount, (String) parameter);
            } else if (parameter instanceof Integer) {
                preparedStatement.setInt(itemCount, (Integer) parameter);
            } else if (parameter instanceof Long) {
                preparedStatement.setLong(itemCount, (Long) parameter);
            } else if (parameter instanceof Float) {
                preparedStatement.setFloat(itemCount, (Float) parameter);
            } else if (parameter instanceof Double) {
                preparedStatement.setDouble(itemCount, (Double) parameter);
            } else if (parameter instanceof Date) {
                preparedStatement.setDate(itemCount, (Date) parameter);
            } else if (parameter instanceof Boolean) {
                preparedStatement.setBoolean(itemCount, (Boolean) parameter);
            }

            itemCount++;
        }
    }

    public static void closeConnection(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
