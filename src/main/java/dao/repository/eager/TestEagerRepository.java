package dao.repository.eager;

import dao.entities.Question;
import dao.entities.Test;
import dao.repository.DaoRepository;
import dao.services.TestService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestEagerRepository extends TestService implements EagerRepositoryLoader<Test> {

    @Autowired
    private EagerRepositoryLoader<Question> questionEagerRepository;

    public TestEagerRepository(@Autowired EagerRepositoryLoader<Question> questionEagerRepository, @Autowired DaoRepository<Test> repository, @Autowired SessionFactory sessionFactory) {
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
