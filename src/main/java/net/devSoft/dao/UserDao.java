package net.devSoft.dao;


import net.devSoft.domain.User;
import net.devSoft.util.QueryExecutor;
import net.devSoft.util.ResultSetProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import net.devSoft.domain.User;
import net.devSoft.util.QueryExecutor;

import java.util.List;

/**
 * Created by Sujoy on 1/31/2015.
 */
public class UserDao {
    public User getUser(User user) {
        String query = "SELECT id, name, password, is_admin FROM user WHERE user.id = ? AND user.is_admin = ?";

        List<User> userList = QueryExecutor.executeSelectQuery(query, new ResultSetProcessor<User>() {
            @Override
            public User getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setAdmin(resultSet.getBoolean("is_admin"));
                return user;
            }
        }, user.getId(), user.isAdmin());

        if (userList.size() > 0)
            return userList.get(0);

        return null;
    }
}
