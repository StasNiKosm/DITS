package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoRepository<T> {

    SessionFactory getSessionFactoryBean();

    default void create(T t) {
        Session session = getSessionFactoryBean().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(t);
        transaction.commit();
        session.close();
    }

    default void update(T t, Session session) {
        session.update(t);
    }

    default void delete(T t, Session session) {
        session.delete(t);
    }

    default void save(T t, Session session) {
        session.save(t);
    }

    default List<T> findAll(Class<T> tClass, Session session) {
        List<T> list = (List<T>) session.createQuery("From " + tClass.getSimpleName()).list();
        return list;
    }
}
