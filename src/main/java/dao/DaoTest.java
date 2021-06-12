package dao;

import dao.entities.Test;
import org.hibernate.Session;
import provider.AppContextProvider;

public class DaoTest extends AbstractDao<Test> {

    @Override
    public Test findByID(int id) {
        return findByID(id, Test.class);
    }

    @Override
    public void update(Test test) {
        commitOperation(session -> {
            session.update(test);
        });
    }

    @Override
    public void deepUpdate(Test test, Session session) {
        session.update(test);
        DaoQuestion daoQuestion = contextProvider.getBean(DaoQuestion.class);
        test.getQuestions().forEach(
            question -> daoQuestion.deepUpdate(question, session)
        );
    }

    @Override
    public void save(Test test) {
        commitOperation(session -> {
            session.save(test);
        });
    }

    @Override
    public void deepSave(Test test, Session session) {
        session.save(test);
        DaoQuestion daoQuestion = contextProvider.getBean(DaoQuestion.class);
        test.getQuestions().forEach(
            question -> daoQuestion.deepSave(question, session)
        );
    }


}
