package repository.managers.lazy.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.DaoRepository;
import repository.dao.entities.Statistic;
import repository.managers.lazy.LazyManager;

@Service
@RequiredArgsConstructor
public class StatisticLazyManager implements LazyManager<Statistic> {

    @Autowired
    private final DaoRepository<Statistic> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Statistic> getRepository() {
        return repository;
    }

}