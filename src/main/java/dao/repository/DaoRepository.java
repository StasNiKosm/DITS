package dao.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoRepository<T> {

    default void create(T t, Session session) {
        session.saveOrUpdate(t);
    }

    default void update(T t, Session session) {
        session.saveOrUpdate(t);
    }

    default void delete(T t, Session session) {
        session.delete(t);
    }

    default T findById(Class<T> tClass, int id, Session session) {
        return session.get(tClass, id);
    }

    default List<T> findAll(Class<T> tClass, Session session) {
        return session.createQuery("From " + tClass.getSimpleName(), tClass).list();
    }

     Class<T> getTemplatedClass();

}
