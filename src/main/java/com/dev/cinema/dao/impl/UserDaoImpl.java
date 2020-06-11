package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User add(User user) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while adding user. Stacktrace: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User getUser(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting User. "
                    + "Stacktrace: ", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM User usr WHERE usr.email = :paramName");
            query.setParameter("paramName", email);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Error while searching user by email. "
                    + "Stacktrace: ", e);
        }
    }
}
