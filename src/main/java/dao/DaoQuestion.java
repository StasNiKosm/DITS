package dao;

import dao.entities.DaoLiterature;
import dao.entities.Question;
import org.hibernate.Session;

public class DaoQuestion extends AbstractDao<Question> {

    @Override
    public Question findByID(int id) {
        return findByID(id, Question.class);
    }

    @Override
    public void update(Question question) {
        commitOperation(
            session -> session.update(question)
        );
    }

    @Override
    public void deepUpdate(Question question, Session session) {
        session.update(question);
        DaoLiterature daoLiterature = contextProvider.getBean(DaoLiterature.class);
        question.getLiterature().forEach(
            literature -> daoLiterature.deepUpdate(literature, session)
        );
    }

    @Override
    public void save(Question question) {
        commitOperation(
            session -> session.save(question)
        );
    }

    @Override
    public void deepSave(Question question, Session session) {
        session.save(question);
        DaoLiterature daoLiterature = contextProvider.getBean(DaoLiterature.class);
        question.getLiterature().forEach(
            literature -> daoLiterature.deepSave(literature, session)
        );
    }
}
