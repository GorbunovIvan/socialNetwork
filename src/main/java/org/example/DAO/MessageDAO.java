package org.example.DAO;

import org.example.entity.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MessageDAO implements DAO<Message, String> {

    private final SessionFactory sessionFactory;

    public MessageDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Message message) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(message);
            session.getTransaction().commit();
        }
    }

    @Override
    public Message read(String entity) {
        try (Session session = sessionFactory.openSession()) {
            Message Message = session.get(Message.class, entity);
            return Message;
        }
    }

    @Override
    public void update(Message message) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(message);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Message message) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.delete(message);
            session.getTransaction().commit();
        }
    }
}
