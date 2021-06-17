package repository.dao.impl;

import repository.dao.DaoRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class AbstractRepository<T> implements DaoRepository<T> {

    private final Class<T> clazz;

    protected AbstractRepository(Class<T> tClass) {
        this.clazz = tClass;
    }

    public T findById(int id, Session session) {
        return DaoRepository.super.findById(clazz, id, session);
    }

    @Override
    public T findById(Class<T> tClass, int id, Session session) {
        return findById(id, session);
    }

    public List<T> findAll(Session session) {
        return DaoRepository.super.findAll(session);
    }

    @Override
    public Class<T> getTemplatedClass() {
        return clazz;
    }
}
