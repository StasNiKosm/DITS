package dao;

import dao.entities.Question;
import org.hibernate.Session;

public class DaoQuestion extends AbstractDao<Question> {

    @Override
    public void update(Question question) {
            commitOperation(session -> {
                session.update(question);
                //FIXME later
            });
    }

    @Override
    public void save(Question question) {
        commitOperation(session ->  save(question, session));
    }

    public void save(Question question, Session session) {
        commitOperation(session, session_ -> {
            session_.save(question);
            //FIXME
        });
    }
}
