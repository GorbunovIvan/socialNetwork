package org.example;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.time.Instant;

public class Application {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //testSimpleSQLQuery(sessionFactory);

        //UserDAO userDAO = new UserDAO(sessionFactory);

        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("from User WHERE name =: name AND login =: login");
            query.setParameter("name", "Ivan");
            query.setParameter("login", "Eastert");

            boolean IvanIsCreated = !query.list().isEmpty();

            if (!IvanIsCreated) {

                User user = new User();
                user.setName("Ivan");
                user.setLogin("Eastert");
                user.setPassword("12345678");
                user.setRegistrationDate(Timestamp.from(Instant.now()));

                session.save(user);

            }

        }

        HibernateUtil.shutDown();

    }

    public static void testSimpleSQLQuery(SessionFactory sessionFactory) {
//        try (Session session = sessionFactory.openSession()) {
//            Query SQLQuery = session.createSQLQuery("TRUNCATE TABLE users");
//            SQLQuery.executeUpdate();
//        }
    }

}