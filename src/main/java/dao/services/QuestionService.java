package dao.services;

import dao.entities.Question;
import dao.repository.DaoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService implements RepositoryService<Question>{

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
