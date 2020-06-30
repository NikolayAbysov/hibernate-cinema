package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while adding role. Stacktrace: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRole(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session.createQuery("FROM Role role "
                    + "WHERE role.roleName = :paramName", Role.class);
            query.setParameter("paramName", roleName);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error while searching role. "
                    + "Stacktrace: ", e);
        }
    }
}
