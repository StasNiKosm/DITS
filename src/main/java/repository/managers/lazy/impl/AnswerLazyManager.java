package repository.managers.lazy.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.DaoRepository;
import repository.dao.entities.Answer;
import repository.managers.lazy.LazyManager;

@Service
@RequiredArgsConstructor
public class AnswerLazyManager implements LazyManager<Answer> {

    @Autowired
    private final DaoRepository<Answer> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Answer> getRepository() {
        return repository;
    }

}