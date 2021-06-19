package repository.managers.lazy.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.DaoRepository;
import repository.dao.entities.Link;
import repository.managers.lazy.LazyManager;

@Service
@RequiredArgsConstructor
public class LinkLazyManager implements LazyManager<Link> {

    @Autowired
    private final DaoRepository<Link> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Link> getRepository() {
        return repository;
    }

}
