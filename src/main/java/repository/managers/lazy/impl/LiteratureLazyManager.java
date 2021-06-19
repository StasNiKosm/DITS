package repository.managers.lazy.impl;

import repository.dao.entities.Literature;
import repository.dao.DaoRepository;
import repository.managers.lazy.LazyManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiteratureLazyManager implements LazyManager<Literature> {

    @Autowired
    private final DaoRepository<Literature> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Literature> getRepository() {
        return repository;
    }

}
