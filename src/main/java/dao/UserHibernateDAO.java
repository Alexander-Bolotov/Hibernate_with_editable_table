package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUser() {
        Transaction transaction = session.beginTransaction();
        try {
            List<User> userList = session.createQuery("FROM User").list();
            transaction.commit();
            return userList;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserByName(String name) {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM User where name ='" + name + "'");
            List<User> users = query.list();
            return users.get(0);
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean nameIsExist(String name) {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM User where name ='" + name + "'");
            List users = query.list();
            return users.size() != 0;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public boolean idIsExist(long id) {
        return getUserById(id) != null;
    }

    @Override
    public boolean addUser(User user) {
        if (!nameIsExist(user.getName())) {
//            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
//                transaction.commit();
                return true;
            } catch (RuntimeException e) {
                return false;
            } finally {
                session.close();
            }
        }
        return false;
    }

    @Override
    public void deleteUserByName(String name) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(getUserByName(name));
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void editeUser(User user) {
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserById(Long id) {
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("FROM User where id =" + id);
            return (User) session.load(User.class, id);
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public String getRoleByName(String name) {
        User user = getUserByName(name);
        if (user != null) {
            return user.getRole();
        }
        return null;
    }

    @Override
    public boolean userIsExist(String name, String password) {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM User where name ='" + name + "'");
            List users = query.list();
            if (users.size() != 0) {
                User user = (User) users.get(0);
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void deleteUserById(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE User WHERE id =" + id);
            query.executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}