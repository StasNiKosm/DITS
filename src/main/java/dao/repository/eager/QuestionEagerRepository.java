package dao.repository.eager;

import dao.entities.Literature;
import dao.entities.Question;
import dao.repository.DaoRepository;
import dao.services.QuestionService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionEagerRepository extends QuestionService implements EagerRepositoryLoader<Question> {

    @Autowired
    private EagerRepositoryLoader<Literature> literatureEagerRepository;

    public QuestionEagerRepository(@Autowired EagerRepositoryLoader<Literature> literatureEagerRepository, DaoRepository<Question> repository, SessionFactory sessionFactory) {
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
