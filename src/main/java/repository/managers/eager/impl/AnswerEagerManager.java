package repository.managers.eager.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import repository.dao.DaoRepository;
import repository.dao.entities.Answer;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.impl.AnswerLazyManager;

@Service
public class AnswerEagerManager extends AnswerLazyManager implements EagerManager<Answer> {

    public AnswerEagerManager(DaoRepository<Answer> repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Answer load(Answer answer, Session session) {
        answer = getRepository().findById(getRepository().getTemplatedClass(), answer.getAnswerid(), session);
        return answer;
    }

}
