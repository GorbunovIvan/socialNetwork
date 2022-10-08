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

        //DAO userDAO = new UserDAO(sessionFactory);

//        createUserIfNotExists("Ivan", "Eastert", "12345678", sessionFactory);
//        createUserIfNotExists("Alex", "Ali", "qwerty", sessionFactory);
//        createUserIfNotExists("Maxim", "Max", "qwe123", sessionFactory);

        HibernateUtil.shutDown();

    }

    public static void createUserIfNotExists(String name, String login, String password, SessionFactory sessionFactory) {

        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("from User WHERE name =: name AND login =: login");
            query.setParameter("name", name);
            query.setParameter("login", login);

            boolean userExists = !query.list().isEmpty();

            if (!userExists) {

                User user = new User();
                user.setName(name);
                user.setLogin(login);
                user.setPassword(password);
                user.setRegistrationDate(Timestamp.from(Instant.now()));

                session.save(user);

            }

        }

    }

    public static void testSimpleSQLQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Query SQLQuery = session.createNativeQuery("TRUNCATE TABLE users");
            SQLQuery.executeUpdate();
        }
    }

}