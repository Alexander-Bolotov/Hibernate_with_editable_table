package factory;

import dao.UserDAO;

import java.io.IOException;

public interface UserDaoFactory {
    public UserDAO createDAO() throws IOException;
}
