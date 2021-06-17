package repository.managers.lazy.impl;

import repository.dao.entities.Test;
import repository.dao.DaoRepository;
import repository.managers.lazy.LazyManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestLazyManager implements LazyManager<Test> {

    @Autowired
    private final DaoRepository<Test> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Test> getRepository() {
        return repository;
    }

}
