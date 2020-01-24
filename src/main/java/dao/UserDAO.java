package dao;

import model.User;


import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUser() throws SQLException;

    User getUserByName(String name) throws SQLException;

    boolean nameIsExist(String name) throws SQLException;

    boolean idIsExist(long id) throws SQLException;

    boolean addUser(User user) throws SQLException;

    void deleteUserByName(String name) throws SQLException;

    void deleteUserById(long id) throws SQLException;

    void editeUser(User user) throws SQLException;

    User getUserById(Long id) throws SQLException;

    String getRoleByName(String name) throws SQLException;

    boolean userIsExist(String name, String password);
}
