package org.example;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.time.Instant;

public class Application {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = new User();
            user.setName("Ivan");
            user.setLogin("Eastert");
            user.setPassword("12345678");
            user.setRegistrationDate(Timestamp.from(Instant.now()));

            session.save(user);

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateUtil.shutDown();

    }

}