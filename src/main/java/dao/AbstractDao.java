package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.support.AbstractApplicationContext;
import provider.AppContextProvider;

import java.util.function.Consumer;

public abstract class AbstractDao<T> {

    protected static AbstractApplicationContext contextProvider = AppContextProvider.getAppContext();

    protected static void commitOperation(Consumer<Session> operation) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        operation.accept(session);
        transaction.commit();
        session.close();
    }

    public T findByID(int id, Class<T> tClass) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(tClass, id);
    }

    public abstract T findByID(int id);

    public abstract void update(T toUpdate);

    public abstract void deepUpdate(T toDeepUpdate, Session session);

    public abstract void save(T toSave);

    public abstract void deepSave(T toDeepSave, Session session);

}
