package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDao ud = new UserDaoJDBCImpl();
        ud.createUsersTable();
    }

    public void dropUsersTable() {
        UserDao ud = new UserDaoJDBCImpl();
        ud.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDao ud = new UserDaoJDBCImpl();
        ud.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDao ud = new UserDaoJDBCImpl();
        ud.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDao ud = new UserDaoJDBCImpl();
        return ud.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDao ud = new UserDaoJDBCImpl();
        ud.cleanUsersTable();
    }
}
