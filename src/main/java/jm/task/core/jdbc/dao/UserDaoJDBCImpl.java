package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastname VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        execute(sql, "Таблица users создана.");
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        execute(sql, "Таблица users удалена.");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES ( '%s', '%s', '%d')" ,name, lastName, age);
        execute(sql,String.format("User с именем %s добавлен в таблицу",name) );
    }

    @Override
    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM USERS WHERE ID=%d",id);
        execute(sql, String.format("User с ID %d удален", id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("ID"));
                user.setName(rs.getString("NAME"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setAge((byte)rs.getInt("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        execute(sql, "Таблица users очищена.");
    }

    private void execute(String sql, String message) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR");
            System.out.println(e.getMessage());
        }
    }
}
