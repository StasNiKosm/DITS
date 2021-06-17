package repository.managers.lazy.impl;

import repository.dao.entities.Question;
import repository.dao.DaoRepository;
import repository.managers.lazy.LazyManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionLazyManager implements LazyManager<Question> {

    @Autowired
    private final DaoRepository<Question> repository;

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public DaoRepository<Question> getRepository() {
        return repository;
    }
}
