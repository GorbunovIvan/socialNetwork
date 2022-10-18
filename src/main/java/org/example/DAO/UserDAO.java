package org.example.DAO;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDAO implements DAO<User, Long> {

    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.getReference(User.class, id);
            return user;
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}
