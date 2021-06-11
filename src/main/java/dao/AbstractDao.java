package dao;

import dao.entities.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;

public abstract class AbstractDao<T> {

    protected static void commitOperation(Consumer<Session> operation) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        operation.accept(session);
        transaction.commit();
        session.close();
    }

    protected static void commitOperation(Session session, Consumer<Session> operation) {
        operation.accept(session);
    }

    public T findByID(int id, Class<T> tClass) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(tClass, id);
    }

    public abstract void update(T person);

    public abstract void save(T person);

}
