package net.devSoft.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Sujoy on 1/31/2015.
 */
public class ConnectionManager {
    private static final String DATA_SOURCE_URL = "java:/comp/env/jdbc/MealPlanDB";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DATA_SOURCE_URL);
            connection = dataSource.getConnection();
            //        log.debug("new database connection created");
        } catch (SQLException e) {
            //          log.debug("failed to create database connection");
            e.printStackTrace();
        }
        catch(NamingException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
