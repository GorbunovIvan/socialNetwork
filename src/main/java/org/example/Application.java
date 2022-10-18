package org.example;

import org.example.DAO.DAO;
import org.example.DAO.UserDAO;
import org.example.entity.Friend;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

public class Application {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //testSimpleSQLQuery(sessionFactory);

        //DAO userDAO = new UserDAO(sessionFactory);

//        createUserIfNotExists("Ivan", "Eastert", "12345678", sessionFactory);
//        createUserIfNotExists("Alex", "Ali", "qwerty", sessionFactory);
//        createUserIfNotExists("Maxim", "Max", "qwe123", sessionFactory);

        makeFriends(4L, 5L, sessionFactory);

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

    public static void makeFriends(Long userId, Long friendId, SessionFactory sessionFactory) {

//        UserDAO userDAO = new UserDAO(sessionFactory);
//
//        User user = userDAO.read(userId);
//        User friend = userDAO.read(friendId);
//
//        user.getFriendsReceivers().add(friendId);
//
//        userDAO.update(user);

        // in single session.
        try (Session session = sessionFactory.openSession()) {

            session.getTransaction().begin();

            User userInviter = session.getReference(User.class, userId);
            User userReceiver = session.getReference(User.class, friendId);

            Set<Friend> friends = userInviter.getFriendsReceivers();

            System.out.println();
            System.out.println("size - " + friends.size());
            friends.forEach(System.out::println);
            System.out.println();

            friends.add(new Friend(userInviter, userReceiver));

            userInviter.setFriendsReceivers(friends);
            session.update(userInviter);

            session.getTransaction().commit();

        }

    }

    public static void testSimpleSQLQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Query SQLQuery = session.createNativeQuery("TRUNCATE TABLE users");
            SQLQuery.executeUpdate();
        }
    }

}