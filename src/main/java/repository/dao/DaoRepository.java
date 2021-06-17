package repository.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интрфейс реализующий CRUD для БД.
 * @param <T>
 */

@Repository
public interface DaoRepository<T> {

    default void create(T t, Session session) {
        session.saveOrUpdate(t);
    }

    default void update(T t, Session session) {
        session.saveOrUpdate(t);
    }

    default void delete(T t, Session session) {
        session.update(t);
        session.delete(t);
    }

    default T findById(Class<T> tClass, int id, Session session) {
        return session.get(tClass, id);
    }

    default List<T> findAll(Session session) {
        return session.createQuery("From " + getTemplatedClass().getSimpleName(), getTemplatedClass()).list();
    }

    default List<T> executeSql(String sql, Session session) {
        return session.createQuery(sql, getTemplatedClass()).list();
    }

    Class<T> getTemplatedClass();

}
