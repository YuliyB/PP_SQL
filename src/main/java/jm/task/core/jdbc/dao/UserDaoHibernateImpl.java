package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

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
        String sql = String.format("INSERT INTO USERS (name, lastname, age) VALUES ('%s', '%s', '%d')",name, lastName, age);
        execute(sql, String.format("User с именем %s добавлен в таблицу",name));
    }

    @Override
    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM USERS WHERE ID=%d",id);
        execute(sql, String.format("User с ID %d удален", id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        String sql = "SELECT * FROM USERS";

        Session session = getSessionFactory().openSession();
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        userList = query.list();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        execute(sql, "Таблица users очищена.");
    }

    private void execute(String sql, String message) {
        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            System.out.println(message);
            transaction.commit();
        } finally {
            session.close();
        }
    }
}
