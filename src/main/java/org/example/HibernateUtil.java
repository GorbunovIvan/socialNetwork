package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        if (sessionFactory != null)
            return sessionFactory;

        try {

            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.out.println("Fail to build session factory");
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();

        return sessionFactory;

    }

    public static void shutDown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
