package net.devSoft.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sujoy on 1/31/2015.
 */
public interface ResultSetProcessor<E> {
        public E getObjectFromResultSet(ResultSet resultSet) throws SQLException;
}
