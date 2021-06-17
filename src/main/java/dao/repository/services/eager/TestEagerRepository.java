package dao.repository.services.eager;

import dao.entities.Question;
import dao.entities.Test;
import dao.intefaces.DaoRepository;
import dao.intefaces.EagerRepositoryService;
import dao.repository.services.TestService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestEagerRepository extends TestService implements EagerRepositoryService<Test> {

    @Autowired
    private EagerRepositoryService<Question> questionEagerRepository;

    public TestEagerRepository(@Autowired EagerRepositoryService<Question> questionEagerRepository, @Autowired DaoRepository<Test> repository, @Autowired SessionFactory sessionFactory) {
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
