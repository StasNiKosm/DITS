package repository.managers.eager.impl;

import org.springframework.stereotype.Service;
import repository.dao.entities.Literature;
import repository.dao.entities.Question;
import repository.dao.DaoRepository;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.impl.QuestionLazyManager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class QuestionEagerManager extends QuestionLazyManager implements EagerManager<Question> {

    @Autowired
    private final EagerManager<Literature> literatureEagerRepository;

    public QuestionEagerManager(@Autowired EagerManager<Literature> literatureEagerRepository, DaoRepository<Question> repository, SessionFactory sessionFactory) {
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
