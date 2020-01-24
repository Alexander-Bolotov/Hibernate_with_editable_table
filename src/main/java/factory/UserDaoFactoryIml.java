package factory;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import util.DbHelper;
import util.ReadProperties;

import java.io.IOException;

public class UserDaoFactoryIml implements UserDaoFactory {

    public UserDAO createDAO() throws IOException {
        ReadProperties readProperties = new ReadProperties();
        String daoType = readProperties.getProp("typeDAO");
        System.out.println("daoType = " + daoType);
        switch (daoType) {
            case "UserHibernateDAO":
                return new UserHibernateDAO(DbHelper.getSessionFactory().openSession());
            case "UserJdbcDAO":
                return new UserJdbcDAO(DbHelper.getInstance().getConnection());
        }
        return null;
    }
}