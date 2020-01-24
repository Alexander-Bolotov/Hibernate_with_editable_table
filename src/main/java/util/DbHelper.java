package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private static volatile DbHelper instance;

    private DbHelper() {
    }

    public static DbHelper getInstance() {
        DbHelper result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DbHelper.class){
            if(instance == null){
                instance = new DbHelper();
            }
            return instance;
        }
    }
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws IOException {
        if(sessionFactory == null){
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() throws IOException {

        ReadProperties readProperties = new ReadProperties();
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", readProperties.getProp("hibernate_dialect"));
        configuration.setProperty("hibernate.connection.driver_class",readProperties.getProp("hibernate_connection_driver_class"));
        configuration.setProperty("hibernate.connection.url", readProperties.getProp("hibernate_connection_url"));
        configuration.setProperty("hibernate.connection.username", readProperties.getProp("hibernate_connection_username"));
        configuration.setProperty("hibernate.connection.password", readProperties.getProp("hibernate_connection_password"));
        configuration.setProperty("hibernate.show_sql", readProperties.getProp("hibernate_show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", readProperties.getProp("hibernate_hbm2ddl.auto"));

        return configuration;
    }

    private static SessionFactory createSessionFactory() throws IOException {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public Connection getConnection(){
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            ReadProperties readProperties = new ReadProperties();
            String url = readProperties.getProp("urlJdbcConnection");
            Connection connection= DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            return connection;

        } catch (InstantiationException | SQLException | IllegalAccessException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}