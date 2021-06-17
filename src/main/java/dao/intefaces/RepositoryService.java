package dao.intefaces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

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
            return read(id, session);
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

    /**
     * Метод используется для получения прокси объекта с "живой" сессией. Используйте этот метод, когда нужно получить доступ к полям объекта,
     * которые помечены "{@code fetch = FetchType.LAZY}". Ответственность за состояние сессии ложится на пользователя.
     * @param id идентификатор объекта
     * @param session сессия соединения с БД
     * @return объект с указанным идентификатором.
     */
    default T read(int id, Session session) {
        return getRepository().findById(getRepository().getTemplatedClass(), id, session);
    }

    default List<T> getAll() {
        try(Session session = getSessionFactory().openSession()){
            return getRepository().findAll(getRepository().getTemplatedClass(), session);
        }
    }

    default List<T> getAll(Session session) {
        return getRepository().findAll(getRepository().getTemplatedClass(), session);
    }

}
