package org.example.DAO;

import org.example.entity.Friend;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FriendDAO implements DAO<Friend, String> {

    private final SessionFactory sessionFactory;

    public FriendDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Friend friend) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(friend);
            session.getTransaction().commit();
        }
    }

    @Override
    public Friend read(String entity) {
        try (Session session = sessionFactory.openSession()) {
            Friend Friend = session.get(Friend.class, entity);
            return Friend;
        }
    }

    @Override
    public void update(Friend friend) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(friend);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Friend friend) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.delete(friend);
            session.getTransaction().commit();
        }
    }
}
