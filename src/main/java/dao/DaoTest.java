package dao;

import dao.entities.Test;
import org.hibernate.Session;
import provider.AppContextProvider;

public class DaoTest extends AbstractDao<Test> {

    public Test findByID(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Test.class, id);
    }

    public void update(Test test) {
        commitOperation(session -> {
            session.update(test);
            //test.getQuestions().forEach(AppContextProvider.getAppContext().getBean(DaoQuestion.class)::update);
        });
    }

    @Override
    public void save(Test test) {
        commitOperation(session -> save(test, null));
    }

    public void save(Test test, Session session) {
        commitOperation(session, session_ -> {
            session_.save(test);
            DaoQuestion daoQuestion = new DaoQuestion();
            test.getQuestions().forEach(question -> daoQuestion.save(question, session_));
            //test.getQuestions().forEach(AppContextProvider.getAppContext().getBean(DaoQuestion.class)::save);
        });
    }

}
