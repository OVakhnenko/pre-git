package com.vakhnenko.utils;

import com.vakhnenko.entity.Changes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static com.vakhnenko.App.logger;
import static com.vakhnenko.utils.Constants.*;

public abstract class ConnectionUtilHibernate {
    private static SessionFactory sessionFactory;

    static {
        makeSessionFactory();
        createDBIfNotExists();
    }

    private static void makeSessionFactory() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            logger.error("Hibernate create session factory error! Connection is not established", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static boolean createHibernateDB(String createStatement, String tableName, String className) {
        boolean result = false;
        Session session = null;

        try {
            session = sessionFactory.openSession();
        } catch (Exception e) {
            logger.error("Hibernate open session error! Connection is not established", e);
            return false;
        }
        try {
            List<Changes> changes = session.createQuery("FROM " + className).list();
            logger.info("Table \"" + tableName + "\" is exists.");
            result = true;
        } catch (Exception e) {
            session.createSQLQuery(createStatement).executeUpdate();
            logger.info("Table \"" + tableName + "\" is created.");
            result = true;
        } finally {
            session.close();
        }
        return result;
    }

    private static void createDBIfNotExists() {
        if (!(createHibernateDB(CREATE_CHANGES_IF_NOT_EXISTS, CHANGES_TABLE_NAME, "Changes"))) {
            logger.error("Hibernate error! Tables not created!");
            System.exit(DB_CREATE_ERROR_EXIT_CODE);
        }
    }
}
