package service;

import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<User> getAllUsers() throws SQLException, IOException;

    void editeUser(User user) throws SQLException, IOException;

    void deleteUserById(long id) throws SQLException, IOException;

    boolean addUser(User user) throws SQLException, IOException;

    String getRoleByName(String name) throws IOException, SQLException;

    boolean nameIsExist(String name) throws SQLException, IOException;

    boolean userIsExist(String name, String password) throws IOException;

    User getUserById(Long id) throws SQLException, IOException;


}
