package repository.managers.lazy.impl;

import repository.dao.DaoRepository;
import repository.dao.entities.Topic;
import repository.managers.lazy.LazyManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicLazyManager implements LazyManager<Topic> {

    @Autowired
    private final DaoRepository<Topic> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Topic> getRepository() {
        return repository;
    }

}
