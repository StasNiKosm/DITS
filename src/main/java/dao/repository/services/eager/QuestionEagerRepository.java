package dao.repository.services.eager;

import dao.entities.Literature;
import dao.entities.Question;
import dao.intefaces.DaoRepository;
import dao.intefaces.EagerRepositoryService;
import dao.repository.services.QuestionService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionEagerRepository extends QuestionService implements EagerRepositoryService<Question> {

    @Autowired
    private final EagerRepositoryService<Literature> literatureEagerRepository;

    public QuestionEagerRepository(@Autowired EagerRepositoryService<Literature> literatureEagerRepository, DaoRepository<Question> repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
        this.literatureEagerRepository = literatureEagerRepository;
    }

    @Override
    public Question load(Question question, Session session) {
        question = getRepository().findById(getRepository().getTemplatedClass(), question.getQuestionId(), session);
        Hibernate.initialize(question.getLiterature());
        literatureEagerRepository.loadAll(question.getLiterature(), session);
        return question;
    }
}
