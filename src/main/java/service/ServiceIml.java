package service;


import factory.UserDaoFactoryIml;
import model.User;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ServiceIml implements Service {

    private static volatile ServiceIml instance;

    public ServiceIml() {
    }

    public static ServiceIml getInstance() {
        ServiceIml result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ServiceIml.class){
            if(instance == null){
                instance = new ServiceIml();
            }
            return instance;
        }
    }
    public List<User> getAllUsers() throws SQLException, IOException {
        return new UserDaoFactoryIml().createDAO().getAllUser();
    }

    public void editeUser(User user) throws SQLException, IOException {
        new UserDaoFactoryIml().createDAO().editeUser(user);
    }

    public void deleteUserById(long id) throws SQLException, IOException {
        new UserDaoFactoryIml().createDAO().deleteUserById(id);
    }

    public boolean addUser(User user) throws SQLException, IOException {
        return new UserDaoFactoryIml().createDAO().addUser(user);
    }

    public  String getRoleByName(String name) throws IOException, SQLException {
        return new UserDaoFactoryIml().createDAO().getRoleByName(name);
    }

    public boolean nameIsExist(String name) throws SQLException, IOException {return new UserDaoFactoryIml().createDAO().nameIsExist(name);}

    public boolean userIsExist(String name, String password) throws IOException {return new UserDaoFactoryIml().createDAO().userIsExist(name, password);}

    @Override
    public User getUserById(Long id) throws SQLException, IOException {
        return new UserDaoFactoryIml().createDAO().getUserById(id);
    }


}