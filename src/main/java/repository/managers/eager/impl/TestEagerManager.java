package repository.managers.eager.impl;

import repository.dao.entities.Question;
import repository.dao.entities.Test;
import repository.dao.DaoRepository;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.impl.TestLazyManager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestEagerManager extends TestLazyManager implements EagerManager<Test> {

    @Autowired
    private final EagerManager<Question> questionEagerRepository;

    public TestEagerManager(@Autowired EagerManager<Question> questionEagerRepository, @Autowired DaoRepository<Test> repository, @Autowired SessionFactory sessionFactory) {
        super(repository, sessionFactory);
        this.questionEagerRepository = questionEagerRepository;
    }

    @Override
    public Test load(Test test, Session session) {
        test = getRepository().findById(getRepository().getTemplatedClass(), test.getTestId(), session);
        Hibernate.initialize(test.getQuestions());
        questionEagerRepository.loadAll(test.getQuestions(), session);
        return test;
    }

}
