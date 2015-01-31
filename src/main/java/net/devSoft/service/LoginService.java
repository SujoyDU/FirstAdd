package net.devSoft.service;

import net.devSoft.dao.UserDao;
import net.devSoft.domain.User;

/**
 * Created by Sujoy on 1/31/2015.
 */
public class LoginService {
    public boolean isValidUser(User user) {
        boolean isValidUser = false;

        UserDao userDao = new UserDao();

        User retrievedUser = userDao.getUser(user);

        if (retrievedUser != null && retrievedUser.getPassword().equals(user.getPassword())) {
            isValidUser = true;
        }
        return isValidUser;
    }
}
