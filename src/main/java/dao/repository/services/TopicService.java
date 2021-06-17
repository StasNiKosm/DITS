package dao.repository.services;

import dao.intefaces.DaoRepository;
import dao.entities.Topic;
import dao.intefaces.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService implements RepositoryService<Topic> {

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
