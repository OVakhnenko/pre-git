package com.vakhnenko.dao;

import com.vakhnenko.entity.Changes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import static com.vakhnenko.App.logger;

public class ChangesDao extends dao<Changes> {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public ChangesDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Changes item) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            int id = Integer.valueOf(String.valueOf(session.save(item))).intValue(); //serializable to int
            transaction.commit();
        } catch (Exception e) {
            logger.error("Hibernate transaction error!");
            return;
        } finally {
            session.close();
        }
    }

    public List<Changes> list() {
        return null;
    }
}
