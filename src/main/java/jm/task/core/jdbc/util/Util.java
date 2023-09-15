package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydbusers";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        Connection connection;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("DRIVER NOT FOUND");
            throw new SQLException(e);
        }
        System.out.println("CONNECTION OK");
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = getProperties();
                Configuration cfg = new Configuration();
                cfg.addAnnotatedClass(User.class);
                cfg.setProperties(properties);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Could not create session factory.");
                e.getMessage();
            }
        }
        return sessionFactory;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("connection.driver_class", DB_DRIVER);
        properties.setProperty("hibernate.connection.url", DB_URL);
        properties.setProperty("hibernate.connection.username", DB_USERNAME);
        properties.setProperty("hibernate.connection.password", DB_PASSWORD);
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        //properties.setProperty("hibernate.use_sql_comments", "true");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "");
        properties.setProperty("hibernate.default_schema", "mydbusers");
        return properties;
    }
}
