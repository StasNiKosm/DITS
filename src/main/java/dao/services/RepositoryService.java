package dao.services;

import dao.DaoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public interface RepositoryService<T> {

    SessionFactory getSessionFactory();

    DaoRepository<T> getRepository();

    default void create(T entity) {
        try(Session session = getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            getRepository().create(entity, session);
            transaction.commit();
        }
    }

    default T read(int id) {
        try(Session session = getSessionFactory().openSession()){
            return getRepository().findById(getRepository().getTemplatedClass(), id, session);
        }
    }

    default void update(T entity) {
        try(Session session = getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            getRepository().update(entity, session);
            transaction.commit();
        }
    }

    default void delete(T entity) {
        try(Session session = getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            getRepository().delete(entity, session);
            transaction.commit();
        }
    }

}
