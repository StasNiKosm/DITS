package dao.repository.eager;

import dao.services.RepositoryService;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Позволяет избежать ошибки LazyInitializeException
 * @param <T> Entity
 */

@Repository
public interface EagerRepositoryLoader<T> extends RepositoryService<T> {

    T load(T t, Session session);

    default T load(T t) {
        try(Session session = getSessionFactory().openSession()) {
            return load(t, session);
        }
    }

    default Set<T> loadAll() {
        try(Session session = getSessionFactory().openSession()) {
            return loadAll(RepositoryService.super.getAll(session), session);
        }
    }

    default Set<T> loadAll(Collection<T> entities) {
        try(Session session = getSessionFactory().openSession()) {
            return loadAll(entities, session);
        }
    }

    default Set<T> loadAll(Session session) {
        return loadAll(RepositoryService.super.getAll(session), session);
    }

    default Set<T> loadAll(Collection<T> entities, Session session) {
        Set<T> list = new HashSet<>(entities.size());
        for(T t : entities)
            list.add(load(t, session));
        return list;
    }

    @Override
    default T read(int id) {
        try (Session session = getSessionFactory().openSession()) {
            return load(getRepository().findById(getRepository().getTemplatedClass(), id, session), session);
        }
    }

    @Override
    default List<T> getAll() {
        return new ArrayList<>(loadAll());
    }

    @Override
    default List<T> getAll(Session session) {
        return new ArrayList<>(loadAll(session));
    }
}
