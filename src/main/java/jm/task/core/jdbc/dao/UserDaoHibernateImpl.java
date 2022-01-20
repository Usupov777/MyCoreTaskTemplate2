package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "id int auto_increment primary key," +
                    "name varchar(20) not null," +
                    "lastname varchar(20) not null," +
                    "age int not null);").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users;").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            users = session.createQuery("FROM Users", User.class).list();
            transaction.commit();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

    }
}
